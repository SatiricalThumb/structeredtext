A := A+B;
A := A*B;
A := A/B;
A := A MOD B;
A := A ** B;
A := A-B;
A := -B;

IF a THEN
b := 1;
END_IF
;

a := b;
CV := CV + 1;
CV := CV+1;
C:= SIN(x);
TBL_L( Enable := TRUE, Thigh := T#300ms, Tlow:= T#300ms);
Lampe := TBL_Q;
RETURN;

IF d < 0.0 THEN n := 0;
ELSEIF
    d = 0.0 THEN n:= 1;
    x1 := -b / 2.0*a;
ELSE
    n:=2;
    x1 := (-b+SQRT(d))/(2.0*a);
END_IF;

CASE TW OF
    1,5         : anzeige := temperatur;
    2           : anzeige := umdrehung;
    4,6..10     : anzeige := status(tw-4);
ELSE
    anzeige := 0;
    fehler := 1;
END_CASE;