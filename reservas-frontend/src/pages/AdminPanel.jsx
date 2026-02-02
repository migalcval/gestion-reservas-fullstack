import { useEffect, useState } from 'react';
import { ReservaService } from '../services/ReservaService';
import { Container, Typography, List, ListItem, ListItemText, IconButton, Paper, Divider } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

function AdminPanel() {
  const [reservas, setReservas] = useState([]);

  const cargarReservas = async () => {
    const datos = await ReservaService.getAllReservas();
    setReservas(datos);
  }

  const borrarReserva = async (id) => {
    if(!window.confirm("¿Borrar reserva?")) return;
    await ReservaService.deleteReserva(id);
    cargarReservas();
  }

  useEffect(() => { cargarReservas(); }, []);

  const formatearFecha = (fechaISO) => {
    const fecha = new Date(fechaISO);
    return `${fecha.toLocaleDateString()} a las ${fecha.toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'})}`;
  };

  return (
    <Container maxWidth="md">
       <Typography variant="h4" gutterBottom>Panel de Administración</Typography>
       <Paper elevation={2}>
         <List>
           {reservas.map((r) => (
             <div key={r.id}>
               <ListItem 
                 secondaryAction={
                   <IconButton edge="end" aria-label="delete" color="error" onClick={() => borrarReserva(r.id)}>
                     <DeleteIcon />
                   </IconButton>
                 }
               >
                 <ListItemText 
                   primary={r.cliente} 
                   secondary={formatearFecha(r.fecha)} 
                 />
               </ListItem>
               <Divider />
             </div>
           ))}
         </List>
       </Paper>
    </Container>
  );
}
export default AdminPanel;