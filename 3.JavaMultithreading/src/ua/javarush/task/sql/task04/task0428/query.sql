-- Write your code here:
SELECT *
FROM event
WHERE MONTH(CURDATE()) = MONTH(date_time) AND YEAR(CURDATE()) = YEAR(date_time);