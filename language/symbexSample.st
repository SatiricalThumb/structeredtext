PROGRAM old

VAR 
  state1 : STATE;
  B1 : BOOL;
  error1 : BOOL;
END_VAR

  error1 := FALSE;

  CASE state1 OF
    S0 :
        B1 := FALSE;

        IF sw0 AND NOT sw2 
        THEN 
           state1 := S1;
        END_IF;

    S1 :
        B1 := TRUE;

        IF sw2
        THEN
          state1 := S0;
        END_IF;

    ELSE error1 := TRUE;
  END_CASE;
END_PROGRAM
 
PROGRAM new

VAR
  state2 : STATE;
  B2 : BOOL;
  L2 : BOOL;
  error2 : BOOL;
END_VAR
  error2 := FALSE;

  CASE state2 OF
    S0 :
        B2 := FALSE;
        L2 := FALSE;

        IF sw0 AND NOT sw2
        THEN 
          state2 := S1;
        END_IF;

    S1 :
        B2 := TRUE;
        L2 := FALSE;

        IF sw1 
        THEN
          state2 := S2;
        END_IF;

    S2 :
        B2 := TRUE;
        L2 := TRUE;
        IF sw2
        THEN
          state2 := S3;
        END_IF;

    S3 :
        B2 := FALSE;
        L2 := TRUE;

        IF NOT sw2
        THEN
          state2 := S1;
        END_IF;

    ELSE error2 := TRUE;
  END_CASE;
END_PROGRAM


PROGRAM repaired

VAR
  state2 : STATE;
  B2 : BOOL;
  L2 : BOOL;
  error2 : BOOL;
END_VAR
  error2 := FALSE;

  CASE state2 OF
    S0 :
        B2 := FALSE;
        L2 := FALSE;

        IF sw0 AND NOT sw2
        THEN 
          state2 := S1;
        END_IF;

    S1 :
        B2 := TRUE;
        L2 := FALSE;

        IF sw2
        THEN 
          state2 := S0;
        ELSEIF sw1 
        THEN
          state2 := S2;
        END_IF;

    S2 :
        B2 := TRUE;
        L2 := TRUE;
        IF sw2
        THEN
          state2 := S3;
        END_IF;

    S3 :
        B2 := FALSE;
        L2 := TRUE;

        IF NOT sw2
        THEN
          IF sw0 THEN
            state2 := S1;
          ELSE
            state2 := S0;
          END_IF;
        END_IF;

    ELSE error2 := TRUE;
  END_CASE;
END_PROGRAM