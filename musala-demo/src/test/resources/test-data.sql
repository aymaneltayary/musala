
delete from nt_device;
delete from nt_gateway;
INSERT INTO musala.nt_gateway (serial_number, description_name, ipv4) VALUES ('sn1', 'gateway_1', '122.186.10.1');
INSERT INTO musala.nt_gateway (serial_number, description_name, ipv4) VALUES ('sn2', 'gateway_2', '122.186.20.2');
INSERT INTO musala.nt_gateway (serial_number, description_name, ipv4) VALUES ('sn3', 'gateway_3', '122.186.30.3');
INSERT INTO musala.nt_gateway (serial_number, description_name, ipv4) VALUES ('sn4', 'gateway_4', '122.186.40.3');
INSERT INTO musala.nt_gateway (serial_number, description_name, ipv4) VALUES ('sn5', 'gateway_5', '122.186.40.4');


INSERT INTO musala.nt_device (uid, vendor,creation_date,state, gateway_id) VALUES ('111111','vendor_1','2020-04-01', '1', (select id from nt_gateway where nt_gateway.serial_number='sn3') );
INSERT INTO musala.nt_device (uid, vendor,creation_date, state, gateway_id) VALUES ('222222','vendor_2','2020-04-02','1', (select id from nt_gateway where nt_gateway.serial_number='sn3'));
INSERT INTO musala.nt_device (uid, vendor,creation_date, state, gateway_id) VALUES ('333333' ,'vendor_3','2020-04-03','1', (select id from nt_gateway where nt_gateway.serial_number='sn3'));
INSERT INTO musala.nt_device (uid, vendor,creation_date, state, gateway_id) VALUES ('444444' ,'vendor_5','2020-04-04', '0', (select id from nt_gateway where nt_gateway.serial_number='sn2'));
INSERT INTO musala.nt_device (uid, vendor,creation_date, state, gateway_id) VALUES ('555555','vendor_6','2020-04-05', '1', (select id from nt_gateway where nt_gateway.serial_number='sn2'));
INSERT INTO musala.nt_device (uid, vendor,creation_date, state, gateway_id) VALUES ('666666','vendor_7','2020-04-06','1',( select id from nt_gateway where nt_gateway.serial_number='sn1'));


commit;
