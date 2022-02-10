select
	name,
	avg_age,
	discovery_date
from 
	fauna
where
	name like '%fish%';
	
select
	name,
	avg_age,
	discovery_date
from 
	fauna
where
	avg_age >= 10000 and avg_age <= 21000;
	
select
	name,
	avg_age,
	discovery_date
from 
	fauna
where
	discovery_date is null;	
	
select
	name,
	avg_age,
	discovery_date
from 
	fauna
where
	discovery_date < '01.01.1950';
	
	
	