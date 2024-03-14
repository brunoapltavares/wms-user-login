ALTER TABLE s_user."user" add column full_name varchar(100) null
;
update s_user."user" set full_name = name
;
ALTER TABLE s_user."user" alter column full_name set not null
;
