del clinica.sql
"C:\Program Files\MySQL\MySQL Server 5.7\bin\mysqldump" -u userjava -p looproject --password=senhajava > clinica.sql
curl --form arquivo=@clinica.sql http://studioestercampos.com.br/clinica/backup.php
