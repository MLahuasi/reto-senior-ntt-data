using jmlq.nttdata.test.Persistence;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi.Models;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddDbContext<AppDbContext>(opts =>
    opts.UseNpgsql(builder.Configuration.GetConnectionString("Default"))
);

builder.Services.AddControllers();
// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle

// 1) Registrar servicios de Swagger/OpenAPI
builder.Services.AddEndpointsApiExplorer(); // Genera metadatos de endpoints
builder.Services.AddSwaggerGen(
    c => c.SwaggerDoc("v1", new OpenApiInfo
    {
        Title = "jmlq.nttdata.test",
        Version = "v1",
        Description = "API para la prueba técnica de NTT Data"
    })
);

var app = builder.Build();

// Configure the HTTP request pipeline.

// Expone el JSON en GET /swagger/v1/swagger.json.
app.UseSwagger();
// Te da una consola interactiva en /index.html
app.UseSwaggerUI(
    c =>
    {
        c.SwaggerEndpoint("/swagger/v1/swagger.json", "jmlq.nttdata.test v1");
        c.RoutePrefix = string.Empty;  // Swagger UI en la raíz (ej: http://localhost:5000/index.html)
    }
);


app.UseHttpsRedirection();

// var summaries = new[]
// {
//     "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
// };

// app.MapGet("/weatherforecast", () =>
// {
//     var forecast = Enumerable.Range(1, 5).Select(index =>
//         new WeatherForecast
//         (
//             DateOnly.FromDateTime(DateTime.Now.AddDays(index)),
//             Random.Shared.Next(-20, 55),
//             summaries[Random.Shared.Next(summaries.Length)]
//         ))
//         .ToArray();
//     return forecast;
// })
// .WithName("GetWeatherForecast")
// .WithOpenApi();

app.MapControllers();

app.Run();

// record WeatherForecast(DateOnly Date, int TemperatureC, string? Summary)
// {
//     public int TemperatureF => 32 + (int)(TemperatureC / 0.5556);
// }
