-- Write your code here:
SELECT *
FROM event
WHERE date_time BETWEEN DATE_SUB(CURDATE() INTERVAL 2 WEEK) AND CURDATE();