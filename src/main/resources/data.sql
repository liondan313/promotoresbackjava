-- Insertar registros en la tabla 'estatusProspecto' evitando duplicados
IF NOT EXISTS (SELECT 1 FROM estatus_prospecto WHERE estatus = 'ENVIADO')
    INSERT INTO estatus_prospecto (estatus) VALUES ('ENVIADO');

IF NOT EXISTS (SELECT 1 FROM estatus_prospecto WHERE estatus = 'AUTORIZADO')
    INSERT INTO estatus_prospecto (estatus) VALUES ('AUTORIZADO');

IF NOT EXISTS (SELECT 1 FROM estatus_prospecto WHERE estatus = 'RECHAZADO')
    INSERT INTO estatus_prospecto (estatus) VALUES ('RECHAZADO');
