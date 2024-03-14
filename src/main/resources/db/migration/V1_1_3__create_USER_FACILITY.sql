-- -----------------------------------------------------
-- Table s_user.user_facility
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS s_user.user_facility (
  user_id INT NOT NULL,
  facility_id INT NOT NULL,
  created_at TIMESTAMP with time zone NOT NULL,
  created_by INT NOT NULL,
  deleted_at TIMESTAMP with time zone NULL,
  deleted_by INT NULL,
  PRIMARY KEY (user_id, facility_id))
;

insert into s_user.user_facility
  select id, facility_id, current_timestamp, 1, null, null
    from s_user."user"
    where id not in (1)
;
commit
;
