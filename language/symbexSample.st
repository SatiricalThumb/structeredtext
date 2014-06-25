PROGRAM test
    (* complex condition for an assignment which is overridden later *)
    IF a AND b OR c THEN
        c := b AND NOT C;
    END_IF;
    c := TRUE;

    IF intval > 2 THEN intval := intval + 1; 
    ELSEIF intval > -3 THEN intval := 1;
    ELSE intval := 0; 
    END_IF;

    CASE intval OF
    1 : a := TRUE; IF b THEN intval := 3; END_IF;
    2 : b := TRUE; a := FALSE;
    ELSE c := TRUE;
    END_CASE;
    
END_PROGRAM
