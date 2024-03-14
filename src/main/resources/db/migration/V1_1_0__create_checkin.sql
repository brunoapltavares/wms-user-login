-- -----------------------------------------------------
-- Table s_user.check_in
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS s_user.check_in (
  id BIGSERIAL NOT NULL,
  user_id INT NOT NULL,
  app_id VARCHAR(100) NOT NULL,
  app_version VARCHAR(25) NOT NULL,
  logon_at TIMESTAMP with time zone NOT NULL,
  PRIMARY KEY (id))
;

CREATE INDEX ix_check_in__logon_at ON s_user.check_in (logon_at ASC)
;
