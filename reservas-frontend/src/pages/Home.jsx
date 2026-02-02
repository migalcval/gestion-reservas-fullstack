import { Button, Container, Typography, Paper, Stack } from '@mui/material';
import { useNavigate } from 'react-router-dom';

function Home() {
  const navigate = useNavigate();
  return (
    <Container maxWidth="sm" style={{ marginTop: 50, textAlign: 'center' }}>
      <Paper elevation={3} style={{ padding: 40 }}>
        <Typography variant="h3" gutterBottom color="primary">
          Bienvenido
        </Typography>
        <Typography paragraph>
          Sistema de Gestión de Reservas
        </Typography>
        <Stack direction="row" spacing={2} justifyContent="center" marginTop={4}>
          <Button variant="contained" size="large" onClick={() => navigate('/mis-reservas')}>
            Soy Cliente
          </Button>
          <Button variant="outlined" size="large" onClick={() => navigate('/admin')}>
            Soy Admin
          </Button>
        </Stack>
      </Paper>
    </Container>
  );
}
export default Home;