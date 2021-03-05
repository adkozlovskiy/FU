1. SELECT student_id|| ';' || upper(surname) || ';' || upper(name) || ';' || stipend || ';' || kurs || ';' || upper(city) || ';' || to_char(birthday, 'dd/mm/yyyy') || ';' || univ_id
FROM student;

2. SELECT substr(name,1,1) || '.' || surname || '; место жительства-' || city || '; родился - ' || birthday FROM student;

3. SELECT lower(substr(name,1,1)) || '.' || lower(surname) || '; место жительства-' || lower(city) || '; родился: ' || to_char(birthday, 'dd-Mon-yyyy')
FROM student;

4. SELECT name || ' ' || surname || ' родился в ' || substr(birthday, 1, 4) || ' году' FROM student;

5. SELECT surname, name, stipend*100 as stipend FROM student;

6. SELECT upper(surname), upper(name), stipend * 100 FROM student WHERE kurs IN('1', '2', '4')

7. SELECT 'Код-' || univ_id || '; ' || univ_name || '-г.' || upper(city) || '; Рейтинг=' || rating
FROM university;

8. SELECT 'Код-' || univ_id || '; ' || univ_name || '-г.' || city || '; Рейтинг=' || round(rating, 100) FROM university;

9. SELECT COUNT(student_id) FROM exam_marks WHERE subj_id = 10 and mark > 2 GROUP BY subj_id;

10. SELECT COUNT(DISTINCT subj_id) FROM exam_marks

11. SELECT student_id, min(mark) FROM exam_marks GROUP BY student_id;

12. SELECT student_id, MAX(MARK) from exam_marks GROUP BY student_id

13. SELECT surname FROM student WHERE INSTR(surname, 'И') = 1 ORDER BY surname LIMIT 1;

14. SELECT subj_name, MAX(semester) FROM subject GROUP BY subj_name

15. SELECT exam_date, COUNT(student_id) FROM exam_marks GROUP BY exam_date;

16. SELECT e.subj_id, s.kurs, AVG(e.mark) FROM exam_marks e JOIN student s GROUP BY s.kurs, e.subj_id

17. SELECT student_id, AVG(mark) FROM exam_marks GROUP BY student_id;

18. SELECT exam_id, AVG(mark) FROM exam_marks GROUP BY exam_id

19. SELECT subj_id, COUNT(student_id) FROM exam_marks GROUP BY subj_id

20. SELECT COUNT(subj_id) FROM subject GROUP BY semester