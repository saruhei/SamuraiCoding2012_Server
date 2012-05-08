<?php

require_once 'RailsSql.php';

// TODO get match_id and result of match by $_POST


// dummy parameters
$match_id = -1;
$parameters = array(
    array('ai_url'=>'hoge', 'rank'=>1),
    array('ai_url'=>'fuga', 'rank'=>2),
    array('ai_url'=>'foo',  'rank'=>3),
    array('ai_url'=>'bar',  'rank'=>4),
);

$sql =& RailsSql::getInstance();

$match = $sql->select_by_match_id($match_id);
if (count($parameters)==count($match)) {
    // Succeed
} else {
    // Failed
}

?>
