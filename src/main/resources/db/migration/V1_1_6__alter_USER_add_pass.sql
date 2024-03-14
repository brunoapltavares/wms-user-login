ALTER TABLE s_user."user" add column pass varchar(100) null
;
update s_user."user" set pass = '123'
;
ALTER TABLE s_user."user" alter column pass set not null
;
