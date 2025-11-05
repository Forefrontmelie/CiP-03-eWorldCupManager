using CiP_03_eWorldCupManager.Application.Services;
using CiP_03_eWorldCupManager.Domain.Interfaces;
using CiP_03_eWorldCupManager.Infrastructure;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddScoped<ITournamentService, TournamentService>();

builder.Services.AddSingleton<IParticipantRepository, ParticipantRepository>();

builder.Services.AddSingleton<IPairingStrategy, RoundRobinPairingStrategy>();


var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
