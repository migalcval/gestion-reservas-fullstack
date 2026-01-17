import { useState, useEffect } from 'react'
import { ReservaService } from './services/ReservaService'
import ReservaForm from './components/ReservaForm'
function App() {
  const [reservas, setReservas] = useState([])

  const cargarReservas = async () => {
    const datos = await ReservaService.getAllReservas();
    setReservas(datos);
  }

  const borrarReserva = async (id) => {
    if(!window.confirm("¿Seguro que quieres borrar esta reserva?")) return;

    try {
        await ReservaService.deleteReserva(id);
        setReservas(reservas.filter(r => r.id !== id));
    } catch (error) {
        alert("Error al borrar: " + error);
    }
  }

  useEffect(() => {
    cargarReservas();
  }, [])

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial', maxWidth: '800px', margin: '0 auto' }}>
      <h1>Gestión de Reservas</h1>
      
      <ReservaForm onReservaCreada={cargarReservas} />

      <hr />

      <h3 style={{marginTop:20}}>Listado Actual ({reservas.length})</h3>
      
      {reservas.length > 0 && (
        <ul>
          {reservas.map((reserva) => (
            <li key={reserva.id} style={{ marginBottom: '10px', padding: '10px', borderBottom: '1px solid #eee' }}>
              
              <strong>{reserva.fecha}</strong>{reserva.cliente} 
              
              <button 
                className="boton-borrar"
                onClick={() => borrarReserva(reserva.id)}
                style={{ marginLeft: '15px', cursor: 'pointer' }}
              >
                Borrar
              </button>

            </li>
          ))}
        </ul>
      )}
    </div>
  )
}

export default App