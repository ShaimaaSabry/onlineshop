CREATE TABLE IF NOT EXISTS country(
	id INT PRIMARY KEY,
   	name VARCHAR(20) NOT NULL UNIQUE,
	code VARCHAR(5) NOT NULL UNIQUE,
	phone_regex VARCHAR(50) NOT NULL
);

INSERT OR IGNORE INTO country(id, name, code, phone_regex) VALUES(1, "Cameron", "237", "\(237\)\ ?[2368]\d{7,8}$");
INSERT OR IGNORE INTO country(id, name, code, phone_regex) VALUES(2, "Ethiopia", "251", "\(251\)\ ?[1-59]\d{8}$");
INSERT OR IGNORE INTO country(id, name, code, phone_regex) VALUES(3, "Morocco", "212","\(212\)\ ?[5-9]\d{8}$");
INSERT OR IGNORE INTO country(id, name, code, phone_regex) VALUES(4, "Mozambique", "258", "\(258\)\ ?[28]\d{7,8}$");
INSERT OR IGNORE INTO country(id, name, code, phone_regex) VALUES(5, "Uganda", "256", "\(256\)\ ?\d{9}$");


ALTER TABLE customer ADD COLUMN phone_valid BOOLEAN;
ALTER TABLE customer ADD COLUMN country_id INT REFERENCES country(id);