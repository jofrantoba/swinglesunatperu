create or replace function MANTANSQLMiFichero(IDTABLA10 in NUMBER,CODIGOXRAY in VARCHAR2,CODIGOSUNAT in VARCHAR2,operacion in number) return number 
 AS 
 begin 
 if(operacion = 1)then 
 INSERT INTO SQLMiFichero (IDTABLA10,CODIGOXRAY,CODIGOSUNAT) values ( IDTABLA10,CODIGOXRAY,CODIGOSUNAT) ; 
commit; 
return 1; 
 elsif(operacion = 2)then 
 UPDATE SQLMiFichero SET CODIGOXRAY = CODIGOXRAY,CODIGOSUNAT = CODIGOSUNAT where IDTABLA10 = IDTABLA10 ; 
commit; 
return 2; 
 elsif(operacion = 3)then 
DELETE from SQLMiFichero where IDTABLA10 = IDTABLA10 ; 
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
end MANTANSQLMiFichero;