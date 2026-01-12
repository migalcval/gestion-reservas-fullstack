const API_URL = "http://localhost:8080/api/reservas";

export const ReservaService = {

    //------ funcion getALL ---------

    getAllReservas: async () => {
        try {
            const response = await fetch(API_URL);
            if (!response.ok) throw new Error("Error al cargar reservas");
            return await response.json();
        } catch (error) {
            console.error(error);
            return [];
        }
    },

    //------ funcion create ---------

    createReserva: async (reserva) => {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(reserva),
        });
        
        if (!response.ok) {
            const errorData = await response.json(); 
            throw new Error(JSON.stringify(errorData)); 
        }
        
        return await response.json();
    },

    //------ funcion delete ---------
    
    deleteReserva: async (id) => {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE",
        });
        if (!response.ok) throw new Error("No se pudo borrar");
        return true; 
    }
};