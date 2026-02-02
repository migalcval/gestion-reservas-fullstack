import { useState } from 'react';
import { ReservaService } from '../services/ReservaService';
import { TextField, Button, Grid, Paper, Typography, Box } from '@mui/material';

function ReservaForm({ onReservaCreada }) {
    const [cliente, setCliente] = useState('');
    const [fecha, setFecha] = useState('');
    const [hora, setHora] = useState('');

    // Generamos las horas
    const arrayFechas = [];
    for (let i = 8; i < 21; i++) {
        const horaFormateada = i.toString().padStart(2, '0');
        arrayFechas.push(`${horaFormateada}:00`);
        arrayFechas.push(`${horaFormateada}:30`);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!cliente || !fecha || !hora) {
            alert("Por favor rellena todos los datos");
            return;
        }

        const fechaCompleta = `${fecha}T${hora}:00`;
        const nuevaReserva = { cliente, fecha: fechaCompleta };

        try {
            await ReservaService.createReserva(nuevaReserva);
            setCliente('');
            setFecha('');
            setHora('');
            alert("¡Reserva creada con éxito!");
            if(onReservaCreada) onReservaCreada();
        } catch (error) {
            alert("Error al crear: " + error.message);
        }
    };

    return (
        <Paper elevation={3} style={{ padding: '20px', marginBottom: '20px' }}>
            <Typography variant="h5" gutterBottom>Nueva Reserva</Typography>
            <form onSubmit={handleSubmit}>
                <Box mb={2}>
                    <TextField 
                        fullWidth 
                        label="Nombre del Cliente" 
                        variant="outlined"
                        value={cliente}
                        onChange={(e) => setCliente(e.target.value)}
                    />
                </Box>
                
                <Box mb={2}>
                    <TextField 
                        fullWidth 
                        type="date" 
                        label="Fecha"
                        InputLabelProps={{ shrink: true }}
                        value={fecha}
                        onChange={(e) => setFecha(e.target.value)}
                    />
                </Box>

                <Typography variant="subtitle1" gutterBottom>Selecciona una hora:</Typography>
                <Grid container spacing={1} mb={3}>
                    {arrayFechas.map((h) => (
                        <Grid item xs={3} sm={2} key={h}>
                            <Button
                                fullWidth
                                variant={hora === h ? "contained" : "outlined"}
                                color={hora === h ? "primary" : "secondary"}
                                onClick={() => setHora(h)}
                            >
                                {h}
                            </Button>
                        </Grid>
                    ))}
                </Grid>

                <Button type="submit" variant="contained" color="primary" fullWidth size="large">
                    Confirmar Reserva
                </Button>
            </form>
        </Paper>
    );
}

export default ReservaForm;