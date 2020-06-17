DROP TABLE IF EXISTS Tool;

CREATE TABLE Tool (
	toolCode VARCHAR PRIMARY KEY,
	toolType VARCHAR,
	brandName VARCHAR,
	dailyCharge DECIMAL,
	weekdayCharge BOOLEAN,
	weekendCharge BOOLEAN,
	holidayCharge BOOLEAN
);