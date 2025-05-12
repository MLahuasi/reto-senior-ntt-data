using jmlq.nttdata.test.Domain;
using jmlq.nttdata.test.Persistence;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace jmlq.nttdata.test.WebApi.Controller;

[ApiController]
[Route("api/[controller]")]
public class ClientesController : ControllerBase
{
  private readonly AppDbContext _db;
  public ClientesController(AppDbContext db) => _db = db;

  [HttpGet]
  public async Task<IEnumerable<Cliente>> Get()
    => await _db.Clientes.ToListAsync();

  [HttpPost]
  public async Task<ActionResult<Cliente>> Post(Cliente c)
  {
    c.Id = Guid.NewGuid();
    _db.Clientes.Add(c);
    await _db.SaveChangesAsync();
    return CreatedAtAction(nameof(GetById), new { id = c.Id }, c);
  }
  [HttpGet("{id}")]
  public async Task<Cliente?> GetById(Guid id)
    => await _db.Clientes.FindAsync(id);
  [HttpPut("{id}")]
  public async Task<IActionResult> Put(Guid id, Cliente c)
  {
    if (id != c.Id) return BadRequest();
    _db.Entry(c).State = EntityState.Modified;
    await _db.SaveChangesAsync();
    return NoContent();
  }
  [HttpDelete("{id}")]
  public async Task<IActionResult> Delete(Guid id)
  {
    var c = await _db.Clientes.FindAsync(id);
    if (c == null) return NotFound();
    _db.Clientes.Remove(c);
    await _db.SaveChangesAsync();
    return NoContent();
  }
}