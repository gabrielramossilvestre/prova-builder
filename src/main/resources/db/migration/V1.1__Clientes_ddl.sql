CREATE SEQUENCE clientes_id_seq;
ALTER TABLE Clientes ALTER id SET DEFAULT NEXTVAL('clientes_id_seq');