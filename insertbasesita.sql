-- Sentencias INSERT para la nueva base de datos

-- Roles
-- Los IDs se generarán automáticamente si no se especifican
INSERT INTO Rol (rol) VALUES
('Usuario final'),
('Coordinador'),
('Administrador'),
('SuperAdmin');

-- Estados de usuarios
-- Los IDs se generarán automáticamente
INSERT INTO EstadoUsu (estado) VALUES
('Activo'),
('Desactivado');

-- Usuarios
-- Los IDs de Usuarios se generarán automáticamente
-- Asegúrate de que los valores de 'rol' y 'estado' existan en las tablas Rol y EstadoUsu
INSERT INTO Usuarios (nombres, apellidos, dni, correo, contrasena, rol, estado) VALUES
('Nassim', 'Ramirez', 20193265, 'a20193265@pucp.edu.pe', 'dd-mm-aa', 1, 1),
('Fernando', 'Godoy', 20206311, 'a20206311@pucp.edu.pe', 'doge', 2, 1),
('Juan', 'Ulloa', 20206452, 'a20206452@pucp.edu.pe', 'password', 2, 2),
('Jeanpier', 'Gutierrez', 20213805, 'a20213805@pucp.edu.pe', 'pawd', 3, 1);

-- Lugares
-- Los IDs se generarán automáticamente
INSERT INTO Lugar (lugar) VALUES
('Complejo deportivo Sol'),
('Complejo deportivo Luna'),
('Complejo deportivo Marte'),
('Complejo deportivo Saturno');

-- Estados de espacio
-- Los IDs se generarán automáticamente
INSERT INTO EstadoEspacio (estado) VALUES
('Activo'),
('En mantenimiento'),
('Prestado');

-- Espacios
-- Los IDs de Espacio se generarán automáticamente
-- **NUEVO:** Añadimos 'costo' y los campos BLOB ('foto1', 'foto2', 'foto3')
-- Los valores BLOB están como NULL; deberías reemplazarlos con los datos binarios de las imágenes si es necesario.
-- Asegúrate de que los valores de 'idLugar' y 'idEstadoEspacio' existan en las tablas Lugar y EstadoEspacio
INSERT INTO Espacio (nombre, idLugar, idEstadoEspacio, tipo, costo, foto1, foto2, foto3) VALUES
('Balón de oro', 1, 1, 'Cancha de fútbol-Grass Sintético', 50.00, NULL, NULL, NULL),
('Balón de platino', 2, 1, 'Cancha de fútbol-Loza', 40.00, NULL, NULL, NULL),
('Campo celeste', 3, 2, 'Piscina', 60.00, NULL, NULL, NULL),
('Campo naranja', 4, 3, 'Pista de atletismo', 30.00, NULL, NULL, NULL);

-- Estado de reserva
-- Los IDs se generarán automáticamente
INSERT INTO EstadoReserva (estado) VALUES
('Confirmada'),
('No confirmada');

-- Reservas
-- Los IDs de Reserva se generarán automáticamente
-- 'captura' es BLOB, se pone NULL.
-- Asegúrate de que los valores de 'coordinador', 'vecino', 'estado' y 'espacio' existan en las tablas Usuarios, EstadoReserva y Espacio
INSERT INTO Reserva (horaInicio, horaFin, fecha, coordinador, costo, vecino, estado, espacio, captura, momentoReserva) VALUES
('08:00:00', '09:00:00', '2025-05-11', 3, 55.00, 1, 2, 1, NULL, '2025-05-10 09:00:00'),
('09:00:00', '10:00:00', '2025-05-11', 3, 55.00, 2, 1, 2, NULL, '2025-05-10 09:00:00'),
('10:00:00', '11:00:00', '2025-05-12', 3, 55.00, 1, 2, 3, NULL, '2025-05-10 09:00:00'),
('11:00:00', '12:00:00', '2025-05-12', 3, 55.00, 2, 1, 4, NULL, '2025-05-10 09:00:00');

-- Estado de geolocalización
-- Los IDs se generarán automáticamente
INSERT INTO EstadoGeo (estado) VALUES
('Asistió'),
('No asistió');

-- Geolocalización
-- Los IDs de Geolocalizacion se generarán automáticamente
-- Asegúrate de que los valores de 'coordinador', 'espacio' y 'estado' existan en las tablas Usuarios, Espacio y EstadoGeo
INSERT INTO Geolocalizacion (fecha, horaInicio, horaFin, coordinador, espacio, lugarExacto, observacion, estado) VALUES
('2025-05-11', '08:00:00', '12:00:00', 2, 1, 'User denied the request for Geolocation', 'Nadie vino', 2),
('2025-05-12', '08:00:00', '12:00:00', 2, 2, 'User denied the request for Geolocation', 'No vino nadie', 2),
('2025-05-13', '08:00:00', '12:00:00', 2, 3, 'User denied the request for Geolocation', 'The skies turn gray', 2),
('2025-05-20', '08:00:00', '12:00:00', 2, 4, 'User denied the request for Geolocation', 'Nothing can fill this silence', 2);

-- Estado del mensaje
-- Los IDs se generarán automáticamente
INSERT INTO EstadoMensaje (estado) VALUES
('Enviado'),
('Leído');

-- Mensajes
-- El ID de Mensaje (idMensaje) NO es AUTO_INCREMENT en tu nuevo esquema, debes proporcionarlo explícitamente
-- Asegúrate de que los valores de 'transmisor', 'receptor' y 'estado' existan en las tablas Usuarios y EstadoMensaje
INSERT INTO Mensaje (idMensaje, horaEnvio, transmisor, receptor, mensaje, estado) VALUES
(1, '2025-05-11 08:00:00', 3, 1, 'Buenos días, recuerde su reserva', 1),
(2, '2025-05-11 08:02:00', 3, 1, '¿Asistirá al evento de hoy?', 1),
(3, '2025-05-11 08:05:00', 3, 1, 'No olvide confirmar su asistencia', 2),
(4, '2025-05-11 08:07:00', 3, 1, 'Gracias por participar', 2);