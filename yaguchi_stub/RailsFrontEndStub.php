<?php

require_once 'RailsSql.php';
require_once 'HTTP/Request.php';

// settings begin
// $game_engine_url = 'http://murooka.me/echo.php';
$game_engine_url = 'http://path/to/gameengine';
$on_server = false;
// settings end


$ai_urls = array();
if ($on_server) {
    foreach ($_REQUEST['ai_urls'] as $ai_url) {
        $ai_urls[] = $ai_url;
    }
} else {
    $ai_urls[] = 'hoge';
    $ai_urls[] = 'fuga';
    $ai_urls[] = 'foo';
    $ai_urls[] = 'bar';
}

$sql =& RailsSql::getInstance();
$match_id = $sql->next_match_id();
$sql->insert_ai_urls($match_id, $ai_urls);

$request =& new HTTP_Request($game_engine_url, array('useBrackets' => false));
$request->setMethod(HTTP_REQUEST_METHOD_POST);
$request->addPostData('ai_urls[]', $ai_urls);
if (!PEAR::isError($request->sendRequest())) {
    $code = $request->getResponseCode();
    $header = $request->getResponseHeader();
    $body = $request->getResponseBody();

    print_r($code);
    print_r($header);
    print_r($body);
} else {
    echo "Error!\n";
}


?>
