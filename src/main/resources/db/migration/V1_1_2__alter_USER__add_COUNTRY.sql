ALTER TABLE s_user."user" add column country varchar(2) null
;
update s_user."user" set country = 'BR'
;
commit
;
ALTER TABLE s_user."user" alter column country set not null
;
