namespace jmlq.nttdata.test.Domain;

public class Cliente
{
    public Guid Id { get; set; }
    public string Nombre { get; set; }
    public string Identificacion { get; set; }
    public string Contrasena { get; set; }
    public bool Estado { get; set; }
}