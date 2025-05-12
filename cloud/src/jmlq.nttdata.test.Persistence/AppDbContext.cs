using jmlq.nttdata.test.Domain;
using Microsoft.EntityFrameworkCore;

namespace jmlq.nttdata.test.Persistence;

public class AppDbContext : DbContext
{
    public DbSet<Cliente> Clientes => Set<Cliente>();
    public AppDbContext(DbContextOptions<AppDbContext> opts) : base(opts) { }
}