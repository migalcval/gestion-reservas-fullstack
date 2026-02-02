import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

function NavigationBar() {
  const navigate = useNavigate();

  return (
    <AppBar position="static" style={{ marginBottom: '20px' }}>
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGw: 1, marginRight: 2 }}>
          Gestor Reservas
        </Typography>
        <Button color="inherit" onClick={() => navigate('/')}>Inicio</Button>
        <Button color="inherit" onClick={() => navigate('/mis-reservas')}>Cliente</Button>
        <Button color="inherit" onClick={() => navigate('/admin')}>Admin</Button>
      </Toolbar>
    </AppBar>
  );
}

export default NavigationBar;