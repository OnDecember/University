-- Write your code here:
SELECT
    DAYNAME(date),
    COUNT(*)
FROM event
WHERE type = 'REGISTRATION'
GROUP BY DAYNAME(date)
ORDER BY COUNT(*) DESC
LIMIT 1;