package com.jmlq.cuenta_service.service.Impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.jmlq.cuenta_service.dto.ClienteInfoDTO;
import com.jmlq.cuenta_service.dto.EstadoCuentaDTO;
import com.jmlq.cuenta_service.dto.MovimientoReadDTO;
import com.jmlq.cuenta_service.dto.ReporteEstadoCuentaDTO;
import com.jmlq.cuenta_service.exception.EntityNotFoundException;
import com.jmlq.cuenta_service.model.Cuenta;
import com.jmlq.cuenta_service.model.Movimiento;
import com.jmlq.cuenta_service.repository.CuentaRepository;
import com.jmlq.cuenta_service.repository.MovimientoRepository;
import com.jmlq.cuenta_service.service.ReporteService;

@Service
public class ReporteServiceImpl implements ReporteService {

        private CuentaRepository cuentaRepo;
        private MovimientoRepository movRepo;
        private RestTemplate restTemplate;
        private String clienteServiceUrl;

        public ReporteServiceImpl(CuentaRepository cuentaRepo,
                        MovimientoRepository movRepo,
                        RestTemplate restTemplate,
                        @Value("${cliente.service.url}") String clienteServiceUrl) {
                this.cuentaRepo = cuentaRepo;
                this.movRepo = movRepo;
                this.restTemplate = restTemplate;
                this.clienteServiceUrl = clienteServiceUrl;
        }

        @Override
        @Transactional(readOnly = true)
        public List<EstadoCuentaDTO> generarEstadoCuenta(ReporteEstadoCuentaDTO req) {
                // 1) Validar y preparar fechas
                LocalDate fechaDesde = req.getFechaDesde();
                LocalDate fechaHasta = req.getFechaHasta();
                LocalDateTime desde = fechaDesde.atStartOfDay();
                LocalDateTime hasta = fechaHasta.atTime(LocalTime.MAX);

                // 2) Traer nombre de cliente (una sola llamada)
                String clienteUrl = clienteServiceUrl + "/api/v1/clientes/" + req.getClienteId();

                ClienteInfoDTO cliente = restTemplate.getForObject(clienteUrl, ClienteInfoDTO.class);

                if (cliente == null || cliente.getPersona() == null) {
                        throw new EntityNotFoundException("Cliente not found for ID: " + req.getClienteId());
                }
                String nombreCliente = cliente.getPersona().getNombre();

                // 3) Todas las cuentas del cliente
                List<Cuenta> cuentas = cuentaRepo.findByClienteId(req.getClienteId());

                // 4) Por cada cuenta, calculamos su estado y devolvemos un DTO
                List<EstadoCuentaDTO> listado = new ArrayList<>();

                cuentas.forEach(cuenta -> {
                        // 4.1) Movimientos en el rango
                        List<Movimiento> movs = movRepo
                                        .findByCuentaIdAndFechaBetween(cuenta.getId(), desde, hasta);

                        // 4.2) Saldo inicial:
                        // base = saldoInicial guardado en Cuenta
                        BigDecimal base = cuenta.getSaldoInicial();

                        // sumAntes = todos los movimientos de esta cuenta con fecha < 'desde'
                        BigDecimal sumAntes = movRepo
                                        .findByCuentaIdAndFechaBefore(cuenta.getId(), desde)
                                        .stream()
                                        .map(m -> "DEBITO".equalsIgnoreCase(m.getTipoMovimiento())
                                                        ? m.getValor().negate()
                                                        : m.getValor())
                                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                        BigDecimal saldoInicial = base.add(sumAntes);

                        // 4.3) Mapear movimientos a DTOs
                        List<MovimientoReadDTO> movDtos = movs.stream()
                                        .map(m -> new MovimientoReadDTO(
                                                        m.getId(),
                                                        m.getValor(),
                                                        m.getTipoMovimiento(),
                                                        cuenta.getId(),
                                                        m.getFecha()))
                                        .collect(Collectors.toList());

                        // 4.4) Calcular saldoFinal
                        BigDecimal sumRango = movDtos.stream()
                                        .map(m -> "DEBITO".equalsIgnoreCase(m.getTipo())
                                                        ? m.getValor().negate()
                                                        : m.getValor())
                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                        BigDecimal saldoFinal = saldoInicial.add(sumRango);

                        listado.add(new EstadoCuentaDTO(cuenta.getId(), nombreCliente, saldoInicial,
                                        saldoFinal, movDtos));
                });

                return listado;

        }
}