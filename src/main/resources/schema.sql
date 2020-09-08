DROP TABLE IF EXISTS master;

CREATE TABLE master (
  id LONG AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  content1 VARCHAR(250) NOT NULL,
  content2 VARCHAR(250) DEFAULT NULL,
  etc1 VARCHAR(250) NOT NULL,
  etc2 VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS subtype;

CREATE TABLE subtype (
  id LONG AUTO_INCREMENT PRIMARY KEY,
  master_id LONG NOT NULL,
  inserted_datetime LONG NOT NULL,
  inserted_user VARCHAR(20) NOT NULL,
  updated_datetime LONG NOT NULL,
  updated_user VARCHAR(20) NOT NULL,
  FOREIGN KEY(master_id) REFERENCES master(id)
);

DROP TABLE IF EXISTS metadata;

CREATE TABLE metadata (
  attr_name VARCHAR(40) NOT NULL,
  parent_id LONG NOT NULL,
  attr_type VARCHAR(40) NOT NULL,
  attr_value VARCHAR(250) NOT NULL,
  PRIMARY KEY(attr_name, parent_id),
  FOREIGN KEY(parent_id) REFERENCES subtype(id)
);
