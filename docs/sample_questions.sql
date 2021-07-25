INSERT INTO `questions` (`question`, `multiple_answers`, `explanation`) VALUES
("Melyik nem HTML5 elem", 0, "A `blink` nem HTML5 elem."),
("A HTML ID-ket csak egyszer lehet használni.", 0, "Igen, az ID-k egyediek minden oldalon és csak egyetlen elemnek lehet adott ID-je."),
("Melyik nem JavaScript adattípus?", 0, "Habár a double nem része a JavaScript-nek, más nyelvekben - mint Java és C++ - használatos."),
("Melyik doctype helyes a HTML5-ben?", 0, "A `!DOCTYPE html` formát a HTML 5. verziójában adták ki, amely egyszerűbb és rövidebb, mint a korábbiak."),
("Az Array objektum melyik függvénye távolítja el a tömb utolsó elemét és adja vissza azt?", 0, "A pop() eltávolítja az utolsó elemet, míg a push() hozzáad egy elemet utolsókéknt egy tömbhöz."),
("Melyik HTML5 elem definiál navigációs linkeket?", 0, "A `nav` elem a HTML5-ben jelent meg azzal a céllal, hogy a navigációs linkeket egy blokkban tartsa."),
("Mi a jQuery?", 0, "A jQuery definíciója a saját honlapja szerint: A jQuery egy gyors, kicsi és funkció-gazdag JavaScript könyvtár."),
("A `margin-top` és `margin-bottom` tulajdonságok beállításának van hatása egy `inline` elemre?", 0, "Hamis, csak a `block` és `inline-block` elemekre van hatása ezen tulajdonságoknak."),
("Az alábbi kifejezésből melyik érték a `left-margin`: `margin: 5px 10px 3px 8px;`?", 0, "8px: A CSS-ben az alábbi sorrendet követik az elemek: `top`, `right`, `bottom`, `left`."),
("Melyik HTML elemen belül helyezhetünk el JavaScript kódot?", 0, "A `script` elem hivatkozhat akár külső JavaScript fájlra, akár a belsejében tartalmazhat JavaScript utasításokat.");

INSERT INTO `answers` (`question_id`, `answer`, `good`) VALUES
(1, "section", 0), (1, "header", 0), (1, "blink", 1), (1, "main", 0),
(2, "Igaz", 1), (2, "Hamis", 0),
(3, "boolean", 0), (3, "undefined", 0), (3, "string", 0), (3, "double", 1),
(4, "!DOCTYPE html5", 0), (4, "!DOCTYPE html", 1), (4, "!DOCTYPE", 0),
(5, "push()", 0), (5, "join()", 0), (5, "pop()", 1), (5, "map()", 0),
(6, "avigation", 0), (6, "links", 0), (6, "nav", 1), (6, "navigate", 0),
(7, "Egy keretrendszer", 0), (7, "Egy könyvtár", 1), (7, "jQuery?", 0), (7, "egyik sem ezek közül", 0),
(8, "Igaz", 0), (8, "Hamis", 1),
(9, "5px", 0), (9, "10px", 0), (9, "3px", 0), (9, "8px", 1),
(10, "script", 1), (10, "javascript", 0), (10, "JS", 0), (10, "link", 0);
