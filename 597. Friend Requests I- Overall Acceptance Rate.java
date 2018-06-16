# Write your MySQL query statement below
select
ifnull ((select round(count(distinct requester_id, accepter_id) / count(distinct sender_id, send_to_id), 2)
from friend_request, request_accepted), 0)
as accept_rate

/* 算法：SQL写法
** 时间复杂度： O(n) */