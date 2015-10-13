//home tableView list statement
SELECT jobs.is_active, jobs.job_id, job_type.name, customers.customer_name, drivers.driver_first_name, jobs.time_created, jobs.time_departed, jobs.time_eta, jobs.message
FROM jobs
INNER JOIN job_type
ON jobs.job_type_id = job_type.id
INNER JOIN customers
ON jobs.customer_id = customers.customer_id
INNER JOIN drivers
ON jobs.driver_id = drivers.driver_id
