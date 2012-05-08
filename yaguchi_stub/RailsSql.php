<?php

class RailsSql {

    var $db;
    private function __construct() {
        $this->db = sqlite_open('rails.db');
    }

    function __destruct() {
        sqlite_close($this->db);
    }

    private static $instance = null;
    static function getInstance() {
        if (self::$instance == null) {
            self::$instance = new RailsSql();
        }
        return self::$instance;
    }

    function reset() {
        sqlite_query($this->db, 'CREATE TABLE match_id_seq (match_id integer)');
        sqlite_query($this->db, 'DELETE FROM match_id_seq');
        sqlite_query($this->db, 'INSERT INTO match_id_seq VALUES (1)');
        sqlite_query($this->db, 'CREATE TABLE ai_urls (match_id integer, ai_url text)');
        sqlite_query($this->db, 'DELETE FROM ai_urls');
    }

    function next_match_id() {
        $res = sqlite_query($this->db,  'SELECT * FROM match_id_seq');
        if (!$res) {
            echo sqlite_error_string($this->db);
            exit;
        }
        $record = sqlite_fetch_array($res);
        $match_id = $record['match_id'];

        sqlite_query($this->db, 'UPDATE match_id_seq SET match_id = ' . ($match_id+1));

        return $match_id;
    }

    function insert_ai_urls($match_id, $ais) {
        foreach ($ais as  $ai) {
            $query = "INSERT INTO ai_urls VALUES($match_id, '$ai')";
            sqlite_query($this->db, $query);
        }
    }

    function select_all() {
        $res = sqlite_query($this->db, 'SELECT * FROM ai_urls');
        $ret = array();
        while ($record = sqlite_fetch_array($res)) {
            $ret[] = array('match_id'=>$record['match_id'], 'ai_url'=>$record['ai_url']);
        }

        return $ret;
    }

    function select_by_match_id($match_id) {
        $res = sqlite_query($this->db, 'SELECT * FROM ai_urls WHERE match_id = ' . $match_id);
        $ret = array();
        $ret = array();
        while ($record = sqlite_fetch_array($res)) {
            $ret[] = array('match_id'=>$record['match_id'], 'ai_url'=>$record['ai_url']);
        }

        return $ret;
    }

}

?>
