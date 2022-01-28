create or replace function MANTANTANLA15(IDTABLA12 in NUMBER,CODMOV in VARCHAR2,LLAVE in VARCHAR2,DESCRIPCION in VARCHAR2,CODIGOSUNAT in VARCHAR2,operacion in number) return number 
 AS 
 begin 
 if(operacion = 1)then 
 INSERT INTO TANLA15 (IDTABLA12,CODMOV,LLAVE,DESCRIPCION,CODIGOSUNAT) values ( IDTABLA12,CODMOV,LLAVE,DESCRIPCION,CODIGOSUNAT) ; 
commit; 
return 1; 
 elsif(operacion = 2)then 
 UPDATE TANLA15 SET CODMOV = CODMOV,LLAVE = LLAVE,DESCRIPCION = DESCRIPCION,CODIGOSUNAT = CODIGOSUNAT where IDTABLA12 = IDTABLA12 ; 
commit; 
return 2; 
 elsif(operacion = 3)then 
DELETE from TANLA15 where IDTABLA12 = IDTABLA12 ; 
commit; 
return 3; 
 else 
return 0; 
end if; 
exception 
WHEN NO_DATA_FOUND THEN 
return -1; 
WHEN OTHERS THEN 
return -1; 
end MANTANTANLA15;  
  
  
  CREATE OR REPLACE FUNCTION CONSULTATANLA15 RETURN types.ref_cursor 
   IS lista types.ref_cursor; 
 BEGIN 
  OPEN lista FOR SELECT * FROM TANLA15; 
   RETURN(lista); 
   END CONSULTATANLA15; 
