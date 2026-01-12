import { useState } from 'react';
import { ReservaService } from '../services/ReservaService';

function ReservaForm({ onReservaCreada }) {
    
    const [cliente, setCliente] = useState('');
    const [fecha, setFecha] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!cliente || !fecha) {
            alert("Por favor rellena todos los datos");
            return;
        }

        const nuevaReserva = { cliente, fecha };

        try {
            await ReservaService.createReserva(nuevaReserva);
            
            setCliente('');
            setFecha('');
            
            alert("¡Reserva creada!");
            onReservaCreada(); 

        } catch (error) {
            alert("Error al crear: " + error.message);
        }
    };

    return (
        <form onSubmit={handleSubmit} style={{ marginBottom: '20px', border: '1px solid #ddd', padding: '10px' }}>
            <h3 style={{marginTop: 5, marginLeft: 2}}>Nueva Reserva</h3>
            <input 
                type="text" 
                placeholder="Nombre del Cliente" 
                value={cliente}
                onChange={(e) => setCliente(e.target.value)}
                style={{ marginRight: '10px' }}
            />
            <input 
                type="date" 
                value={fecha}
                onChange={(e) => setFecha(e.target.value)}
                style={{ marginRight: '10px' }}
            />
            <button type="submit">Guardar Reserva</button>
        </form>
    );
}

export default ReservaForm;