import { useState } from 'react';
import { ReservaService } from '../services/ReservaService';

function ReservaForm({ onReservaCreada }) {
    
    const [cliente, setCliente] = useState('');

    const [fecha, setFecha] = useState('');
    const [hora, setHora] = useState('');

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

            <label style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>Elige una hora:</label>
            <div style={{ 
                display: 'grid', 
                gridTemplateColumns: 'repeat(4, 1fr)', // 4 columnas
                gap: '10px', 
                marginBottom: '20px' 
            }}>
                {arrayFechas.map((arrayFechas) => (
                    <button
                        key={arrayFechas}
                        type="button" // ¡IMPORTANTÍSIMO! Para que no envíe el formulario al hacer click
                        onClick={() => setHora(arrayFechas)}
                        style={{
                            padding: '10px',
                            cursor: 'pointer',
                            border: '1px solid #ccc',
                            borderRadius: '5px',
                            backgroundColor: hora === arrayFechas ? '#3498db' : '#f8f9fa', 
                            color: hora === arrayFechas ? 'white' : 'black',
                            fontWeight: hora === arrayFechas ? 'bold' : 'normal'
                        }}
                    >
                        {arrayFechas}
                    </button>
                ))}
            </div>

            <button type="submit">Guardar Reserva</button>
        </form>
    );
}

export default ReservaForm;