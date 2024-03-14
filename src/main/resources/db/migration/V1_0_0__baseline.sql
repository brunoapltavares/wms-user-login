-- -----------------------------------------------------
-- Table s_user.user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS s_user.user (
  id SERIAL NOT NULL,
  external_id VARCHAR(100) NOT NULL,
  name VARCHAR(100) NULL,
  email VARCHAR(100) NULL,
  facility_id INT NULL,
  created_at TIMESTAMP with time zone NOT NULL,
  updated_at TIMESTAMP with time zone NULL,
  deleted_at TIMESTAMP with time zone NULL,
  created_by INT NOT NULL,
  updated_by INT NULL,
  deleted_by INT NULL,
  PRIMARY KEY (id))
;

insert into s_user.user (id, external_id, name, email, created_at, created_by)
    values (1, 'SYSADM','System Admin','sysadm@mercadofavo.com',current_timestamp, 1);
commit;