package ics.recents;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import java.util.HashMap;

/** Real Android 4.4 (KitKat) status bar icons from TechnoUI (white, xhdpi) embedded as base64. */
public class KkIcons {
    static final HashMap<String, String> D = new HashMap<String, String>();
    static {
        D.put("sig0",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAAAVFBMVEUAAAD/////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////////8wXzyWAAAAHHRSTlMAMCpmIWUaZBZhEF5b" +
            "Mi0oIx9jYjErJhwYYDQzcBB7iAAAAHJJREFUOMu90L0SQ1AYAFGyLiLEb4jP+7+nNk1mtjC2PtVm2XXljj1QrEjKlRXG1U+Ma14o" +
            "1+Icl7rOuTfK9Sg3JOXGCuPKCeVmnEO5xbkPyq0oVyTltt9x/913Rrkd51AunAuUC5QLlAuUO1DuwLmbOwEwxAZ3jadFkwAAAABJ" +
            "RU5ErkJggg==");
        D.put("wifi0",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAABAlBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////////////////////////////////////////95" +
            "EvdTAAAAVnRSTlMAAw0QFA4KJzxPXWZjV0YyHQQSOFhKJgUCUmE+ASpaZRhkSTYzOUJfFzBVGgcPREc9BiM6XkUoIkhbHlQlC04R" +
            "KVkrYi81P0NQJAgVQB8WTFwxLEFNYIfxOlgAAAGWSURBVDjL1VTZQoJAFB0FNB21UNRRUSNwRVzSFsMlJcsNbbH//5WGIAUE7KWH" +
            "ztPMcIa5595zLwB/DJ+fIEkq4EWhgmehMNQRicbOL2gHUjzBQDsiyVTaQkKpDHRGNkfsWWy+AN1RjBnMywz0BpfAr/sSRfPl6FWO" +
            "F/hSOVQxM5kqqB12dV7EF+kARTXwM1Kqmd1/qgC2ZWhrk6Bz3Q319B1zUyNB+tYIiRPxfW3NlQLo7p6zxtXnJfAga6uBpmPYh6Mx" +
            "epw4aW2O0VSBvJHiJ1asu2ntpiVhn8FZ0T0rTPxQj5TpOPncbr/Mlf2B3DEVjtcfiS2WeEP7cI1IQa93j7JUuKyFMgQrIVnAv1Lk" +
            "9aABVJwJZWO1C9qOJBSUTXEpa4KdZlW7r2i2atfMlejOsQHTr8da35zsvFHstBbr6HtVz2JYZiLfiznt0iB5CCfvfk2V+MFBZuna" +
            "SV0BBWqt+S6nsuOd5NFyaBYxqrDy7F56/qNh4d3mS6M7a6fmAfGp0XKnB4eIXb1Fv5gwC6uV3JEfgn+HLxqyNye36fFoAAAAAElF" +
            "TkSuQmCC");
        D.put("sig1",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAAAWlBMVEUAAAD/////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////////////////9ZMre9AAAAHXRSTlMANi1mJB9l" +
            "GWISYF0xLCciHmQaYS8qJSAcGH59X6XuT+cAAAB+SURBVDjLxdDLDoIwAAXR4oVaiqKioBb7/7/Jw42QNJlFE2d9VmNMvgrGDkKs" +
            "FHKVRe7oRFzthVwj5pTVnZg7C7lWyF0sctebiOuckLuLOSH3YK4XcoOQe1rkXr/j0u69GZd2XswJucBciMiFiNzMiFsYcGNE7sv2" +
            "7rNm/tcERjIJNDw0VecAAAAASUVORK5CYII=");
        D.put("wifi1",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAABNVBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////////9vAW14AAAAZnRSTlMAAw0QFBMOCic8T1xm" +
            "Y1dGMhwEEjhYYkgmBShSYT8BKlsPGWRlST42NDpCXxcwVRoHRF5HFQYiRS8pU1QCHyULThFZQyw5MzVKPVAkCUAWTDFdKwh4scjA" +
            "lkuD+NP+2y3wn/m90mh7eN5uAAABo0lEQVQ4y9VUWXvBQBQdoqGMIiSVIo01pYraShO11trSRXXVvf7/T+hEfAgRXvrQ8zTLme/O" +
            "PffcC8AfQ6PFdFu4Xo2CG7aNJijBvGOx2ggFkt1BwkVQu05aHmzPBZXh9tinLGafhSqweCWazwXV4Xeg6BpHYO4osBMMcQdcOGI8" +
            "nGeSUXA028W4OHpI6HFtAoXxOo+p6VUSMKmJEKE0yGTDxuRElpOcDtD500nkOHrPi4uwXsgG/fJ/sRwOCuItPBPzKLKwVBbyFYUM" +
            "AtWyUKMgN5E4x/hiq3I9p731qYKNwGpVyJnQwDA7rjQ9rVa1PUuVz8wVjpOCWDoXaEMIqEbpulTvJC6rcETMuQjS1mbFjQzAX3YS" +
            "TAEViurK7SL0Sphg4OfN4sGYmruw6CuCicaWciUyywakTcu5XinZuUst0lKMou8LkorXPGkeL9rEiga5QfLVtKK/4xE/JG9XdlK4" +
            "Lvj6d4P7h56APWIqLUc/PY/GeGmodq9+INFGQ1a9zdOvEq+/bh68vYu0j/WDI/I5Gn1lNpgw7PDbu9Eo+rGBf4dfdOlGSg92HUYA" +
            "AAAASUVORK5CYII=");
        D.put("sig2",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAAAaVBMVEUAAAD/////////////////////////////////////////" +
            "///////////////////////////////////////////////////////////////////////////////////////////////8G612" +
            "AAAAInRSTlMANi1mJB9lGWISYF0xLCciHmQaYXZp/lxQ/EX5O/Xwfn1fUv61kAAAAHpJREFUOMvFyjsSglAURMEno4g/EFAUVC7O" +
            "/hdplTHBCaii405pORvWMqG2FXq7HL19IfIOR6F3Enta9J3Zuwi9UuhVOXrXWuQ1hdBrxZ7Qu7F379B7dCbv2Zu84WXy3h+jN5o9" +
            "oxfshdELoxdGL4zeZPQmz73vX1rPD86NEfJJ1Bg1AAAAAElFTkSuQmCC");
        D.put("wifi2",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAABblBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////////////////////////+DpEQuAAAAeXRSTlMAAw0QFBMOCic8T1xmY1dGMhwEEjhYYkgmBShTYT8B" +
            "KlsZUmRlST42NDpCXxcwVRoHD0VeRwYiLylUOQIfJAtNEVlDLDM1Sj1QCAkVfrne9NGfFkxO9p7V+47nezf1sfzo0tv4yGxtIKSS" +
            "f3jAlku8g/7cLfCb+b1oA/2khQAAAa9JREFUOMvVVFlbgkAUHZfAjLRILUwJ00StXDKNRM32xVLbbbM02/e9+feh8IkYUC89dJ7u" +
            "zBy+yzl3AeCPodHq9G0IqkZBDO3GDoxHp8nc1Y3LkHosVqwVtt4+QprM3o/Jw+HsabDIAQpTgRnhaa5BTB1uC5ddY/E0XXlMQ17a" +
            "R/sDxuFmpnUEjIqnIB3iPsRRRBvm0kTsY7bGUxSQ44IRXj2Ixf3GqGDL2IQeEIwgzx0CgJisBQmUjQ+5pf9F0QhI1l6xVE1HmsKm" +
            "MiwzLaPAM5NhZ20YLVg8QbqCSloTRGSu4eC8R9kVa0asByNeTy84FxdnlkSpk7GmwtF8EnNqmTuscO6QWR9f7ygiqXCAY/nTwLe6" +
            "lstDmC+sUzoyyRXKtiFtF3ZzK4I7ClDE9o6BnHUkW/sKJxO7UIriHhr73oDaffgNB3LtfHjUSitpZPt+67j+elKunNaDqtKUnEGY" +
            "O7/ggnDgsggrWcVJurrGXTel6u3dPcs8pFRGjnjkU8KyT3V60aog4YlSH/PsM8+7+WkfvLzWaG8/L47AO4QfsV9sGOqpHPnVKvrs" +
            "Bv8OX/oGb1qeIcqhAAAAAElFTkSuQmCC");
        D.put("sig3",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAAAZlBMVEUAAAD/////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////////////////////////////////+Vn2moAAAA" +
            "IXRSTlMANi1mJB9lGWISYF16bWH+VfxL+UL18nZpXFBFO/B+fV/7QXyYAAAAgUlEQVQ4y8XQ0RKBUBRG4ZONEFJUUnb+93/JUrdm" +
            "zrpoxrr+rlYI65UwtjHEtobcbo9cejDijidDLjPmbFV3Zu4i5K45crdCxJV3EfeohFwt5oRcw9wzR66dxgH3+o6Lu24eF3X9Mi7q" +
            "3mJOyDlzLuRcyLmQcyE3CLlBv9xnLvyvEetWHsOFIsoPAAAAAElFTkSuQmCC");
        D.put("wifi3",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAABxVBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////////////////////////////////////////////////////////AbUpCAAAAlnRSTlMAAw0QFBMOCic8UFxmY1dGMhwS" +
            "OFhiSCYFKFNhPwEqW0UZUmRlVkk+NjQ6Qk9fFzBVGhgiIREPXkeRxer42ax2KQZUObf75IgjHyQLTQRn3/2nAiw1L9WPDDOG2ytp" +
            "3ridmajJ9NKH+rAVCYHdIKT5kBt+ufzo0Z/O9Te9wyXT9p5u8k6N57HIb22SeMCWS7yE/i3wm2hxrtw0AAABuUlEQVQ4y2NgoDFg" +
            "ZGJmYWVjx6eEjYOTi5sHAnj5+AUEGbEoEhIW4UEHomLiEqiWSUrxYAfSMkJwVbJy8jx4gAIbRJmiEg9+oCwMtJ1RWAVJSEVVTV1D" +
            "U0NLW0cXWaWIHoM+gmegYQjUyG5kbGIqy8BgJqkgCpcyZ5C1gAaEOgsDs6qllfU0ELCxtbMXkJVwgHpP2ZCBwdEJxHBmlzBwcZ2G" +
            "AtzcPRg8DUDqvED+8Jbn8TH19fOfhgkCAuWCgkV5QqBBHCorEzYNO3ANjzCLhIdgVMA0nCA6BhEfsQjhuPiExMSk5BS4QKoZUsSl" +
            "gYXSM+QzgZwsYKgwhmTngMWsc5EjWDZv2rT8gkIGzaLiuJRpJaVl5fLMQToV06ZVVqEmF8fqmlpf6Tokd9U3cDA2NjWjpytvRi10" +
            "P7u2ZJlhJkCmVky/2mNLzlWV6MraGLGm++YSsGxparQNmNGOK5d0TJuW0wkKBwn1PNdp0Zk4c1JXka9hd1t7T29fkEO/F54sJzHB" +
            "BhoLmnhzL3s71AsT5fFn80xo8uomVB5MmgxSNoVwwaGdP21atSMRJYz8xFQzooqiqYIMQw4AANl8zLjGSfQwAAAAAElFTkSuQmCC");
        D.put("sig4",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAAAXVBMVEUAAAD/////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////////////////////9RKvvlAAAAHnRSTlMAAYdx" +
            "W/5O/D71LfDpem1hVUv5QvJ2aVxQRTt+fe78tj9iAAAAg0lEQVQ4y8XQSQ6CQBQA0XZoBXHE2Y91/2Ma3YiJQC1MrPVbVUrDjZJr" +
            "7NgExaZZudkc44oS4xYVyi1xjp+6lXNrlNtk5bbPwcNuV2PcvkS5A86h3NG5U1bu/B7X5y6tcT3u2h7X7W4f47pdhXMoF84FygXK" +
            "BcoFyjUo13wF91fpfz0AR2Es0evZQuYAAAAASUVORK5CYII=");
        D.put("wifi4",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAABy1BMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////////9J6b8RAAAAmHRSTlMACCAoMzAiFyRhlcfn" +
            "99mvfEWN2/W1YAxk0fOdLAJp5f6sPs75jA0H/di3nIiDkKbG7tw5d9SFQgoYIREBJmOu9OwDs5oQVpHF7fh2MWbQD9L7jxkFVeNO" +
            "WxzBKgRn36dugS11QNWOgIYr3ZmoyYf6sBUJNd7qHqQbNH65n083Br3DJdP2nifyCzqx/MhzbZJ9GnjAlkvwaD8bwxwAAAG6SURB" +
            "VDjLY2CgMWBkYmZhZWPHp4SDk4ubZwYE8PLxCwgyYlHEKiQ8Ax2IiIqJo1omITkDO5CSloGrkpWTn4EbKChyQJQpKc/AD1RU1RgY" +
            "1IU0kIQ0NLW0dXR19PQNDJFVGhkzmCB4pmbmQI0WlmxW1jYMNrZ29g5wKUcGJ2doQLiwMLi6uXt4gnlefIrePjZqvn5Qm80ZGPwD" +
            "gIzAoOCQUK1AVHeF6bDZhJuCvBIB8kdk2Iyo6JjYOCw+0IiPjkkQmZEI8XBSsmxKKg6/BqaJ26bbwEIwQwN3qAhnIuJDDiGcpaWd" +
            "nZ2Ti/BqgD9SxOWBhfILIgqBnKIQBgan4sQSsFhpGXIE25QDPa9XwVBZVV0CNMpBsibCVbYWGFEidajJpb6hsSmGsxnJXS2tbU4J" +
            "7eHo6arDqRPdz4FCRf6YCVCNB9Ov3tiSc50IurIuJ6zpPrwbLMsTIOwFZvRY4MggvcDg62MCMkKUygNnCBfizEn9E2ImTurqmTxl" +
            "qmx0NQeeLBeS4QWNBRa8udeiB5YrIvBn88IwiLpJhMqDpGkgZVMIFxzmKjNmNNQTUcJEKAT4E1UUTY9kGHIAAG1aL+rwC2nVAAAA" +
            "AElFTkSuQmCC");
        D.put("wifi_in",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAAArlBMVEUAAABSUlIYGBgXFxcVFRUdHR06OjoxMTFTU1MZGRkZGRkX" +
            "FxcfHx85OTk2NjYLCwsyMjICAgICAgICAgICAgIFBQUcHBwaGhoAAAAAAAAAAAAAAAAAAAAAAAALCwsDAwMAAAAAAAAAAAAAAAAA" +
            "AAAAAABEREQJCQkAAAAAAAAAAAAAAAAAAAAAAAAAAAAbGxsAAAAAAAAAAAADAwMCAgIICAhcXFxjY2NJSUlDQ0P+IIz6AAAAOnRS" +
            "TlMAAQICAgIBASVFPz5FGAEBJIiHhop6EgEBV4h9f345AQIdgYCFcQsBU4k8GYRyCwGHPXVbQwEQCQEBEAWgsAAAAI1JREFUOMtj" +
            "YBgFo2DoAUYmZhBgYWWjhmnsDBycXCDAzcPLwIdbHT+DgKAQEAiLiIoxiOMxUIJBUkpaRkZWSo5BHp/FCgyKMkrKMiqqagzqeF0o" +
            "waChKSsjpQVkEFCnLaOso6unT0Adg4SEhoGBISHjwAbqGOkRVgdUYWzCYEpMaJuZM1hQMy1YDuN0DgAi7QmtFS5mdQAAAABJRU5E" +
            "rkJggg==");
        D.put("wifi_out",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAAAtFBMVEUAAAAUFBQYGBgZGRkTExMAAAABAQEBAQECAgIAAAAAAAAA" +
            "AAAAAAAAAAAAAAAAAAAAAAALCwsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANDQ0JCQkAAAAAAAAEBARDQ0MKCgoAAAAA" +
            "AAADAwMJCQkFBQUFBQUFBQUFBQUICAghISEQEBBiYmJUVFRYWFhYWFhYWFhXV1dbW1tkZGRdXV1UVFROTk5NTU1MTExTU1NTjhgn" +
            "AAAAPHRSTlMAAR0TAQJpUQElh3oSAV+GSAEkhH+DeREDiH6JRwEpgHkSAWSKTS2IgoOEgBkBARstKSopLhIBAQEBAQE/c9b+AAAA" +
            "i0lEQVQ4y2NgGL6AkZG65jExM7AQoYyVjZ2Bg7AqBk4ubh4GXoIKefn4uQQYBAmpYhASFhEVEydkIK8En6SUiLQMgywB18kJy8tL" +
            "iSooMijhV6gsKQVUqKKK18tKDGrqGpqamhpa2joMurjV6THoGxgaGRkZGpuYMphRI87MLSwtgMDKmmEUjIKhBwDKzApgC1GubgAA" +
            "AABJRU5ErkJggg==");
        D.put("wifi_inout",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAABIFBMVEUAAAAUFBQaGhoYGBgZGRkTExMAAAABAQEBAQECAgIAAAAA" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMDAwAAAAAAAAAAAAAAAAAAAAAAAAAAAANDQ0GBgYAAAAAAAAAAAAEBARGRkYLCwsAAAAA" +
            "AAACAgIKCgoJCQkFBQUFBQUFBQUFBQUICAghISFiYmJUVFRYWFhYWFhYWFhXV1dbW1tmZmZfX18wMDBQUFAZGRkZGRkZGRkXFxcg" +
            "ICA/Pz87OzswMDACAgICAgICAgICAgIGBgYjIyMfHx8AAAAAAAALCwsDAwMAAAAAAAAAAABCQkIICAgAAAAAAAAAAAAAAAAAAAAX" +
            "FxcAAAAAAAAAAAADAwMDAwNbW1tgYGBJSUlERET1rR+lAAAAYHRSTlMAAQEfFQECalABJYh5EQFghodHAYR/g3hffolGASmFgHoT" +
            "AWWKTQEviIKDhH8YARwtKSopLREBASZEPj8+QxcBJIqHhot8EgFWfT0BGoFwCQFVORtxCgFUO3NeRBEMAQHGaygSAAAA7ElEQVQ4" +
            "y2NgGL6AkZGapjExMLMwsBKhkI2dg4GTsCoGLm4eXgY+ggr5+AUEhRiECali4BIRFRMnaCAfmwS3pKiUNIMMAdfJysnLS4opKDIo" +
            "4VeozC0JVKiiitfLagzqGppaWlqa2jq6DHhiRY9B38DQyMjI0NjElMGMfinFnMHC0soaCGxs7RjscatTYnBwdAICZxdXNwZ3vNHh" +
            "IegpKiop6MXgjT+cfUR95UXl/PwZAvDHG1+glKQkdxDB+GUIlpcXCQkN4yOYYMIFBSMIpz+gC+UiQ4lJpwxR0QQ8AQUxsQxx1Iy9" +
            "+GFchgEAqRgZFQ9olNcAAAAASUVORK5CYII=");
        D.put("d3g",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAABblBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////////////////////////+DpEQuAAAAeXRSTlMAAwIBLVRFFS9QTSiJ69dLFKDu6JIGbcnk5hye/sTI" +
            "bsHDDfpT9v2RCg6u2AVCVyRbPkBZ2sUZOV71PBYRBFaq2/yZPQkIh/hR97TPzLnwZDjW8iET7XQ7HYUfeZVG4IZBag/cchKdnOwg" +
            "f+/pJY3Qv5Dzi6IxP0MeeOGK9wAAAUZJREFUOMvtUldTAmEMzF1EUFREBRuICneCHiIWsCsCAooFsffee99/L6Dj0/f9AGfcl+xM" +
            "djaZZImKUBRFLVYuEiYZSh31pxLLVWWmcjNxgVgqKq1yt6pqoMZGXGuvA+obHCyyVMgJNDahuYVa4XK3AR5SBW7saEdHp8kLn1nT" +
            "u/yBbvQYgh2ZjGCvhUKa3heGt59oYHAoIjL8hk1HdNg1MspsjI0rYs2EMjk1HZthJ7Q4JZKGIbmMQrNAKk0eZKKOufnsQkR8Q5Wt" +
            "aTsWQ0vILCdywEpevt/qGtZ90DZocyvWtC3QMQV2dvfYv68fxOE9JDqCRHd8ArcRTOH0LOM6TxoXscsr0VyVroHcDW7v6L5AHoBH" +
            "v+hxzP6nZ+jZMKmJlxtAe41II5MPvyVLzcj7x6c8fr/5KxFW5Ur1J8W/5B//+Iv4ApojOmMnGUznAAAAAElFTkSuQmCC");
        D.put("d4g",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAABSlBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////////////////////////////////////9mcS2d" +
            "AAAAbXRSTlMAAQIDCWScgRMVfLXFp2Ij7+0lHMuOivzij/J9Q6n99TTo5R4Fyqof93oEvRoX4YgMXnk4JPG7eOQY7oRaaBuLMV9d" +
            "ZS8Z3QZr4A2N/oOAT6gt6oxKlD3Uh6OezfaaxK5z+kwRuEWbCG2ktKFmnRIteQAAAThJREFUOMvtktdPAkEYxGd3FQRF7kQpoqcU" +
            "RRFUUBGxYKMoJ/Yu9q7f//8qB4nRZO989YFfsk87mc3sDPALxhis4IIbKtE8pjRN6lLeZmmHdpu9A3A4O7tc3Rwmb3PmVkjtgaeX" +
            "DPpMdfCSz+9GgPqDA4Pa0DCEiV3IR+FIdEQbBWJjNI643C5AExROJCdpikWnU+kZI5HEbnYuM0/ZhViOvBZRBRZpaZmyKxGV8lhd" +
            "W9/YlAXhKISLpTJtbXtUqsBGOu3IcnBUKbO7R0pq/4AOcXSc04PyvI1f00k5OaUzBoefnHJd2XV+cUlXtcS1dgMUQloVph3f+oqR" +
            "+m/fVe5rGqXlOgbB7EZvD4+N3jJJ8+Kenqv1Hby8vr1/fJZgvcHGNf9emdwxHmfGRjmE+MPt52JbtPhffAFzYjCpRpCwbgAAAABJ" +
            "RU5ErkJggg==");
        D.put("de",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAAAsVBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////////////////////////////////+3mHKcAAAA" +
            "OnRSTlMAAQMCQ52Qj5NzeeEEcPzpe2ZqVHHdGBdy6GlPWVIO/S7zwbe+sCDeIgcG39wUfWhsWHpBmo6NjJFvalQYbgAAAJ5JREFU" +
            "OMvtkkcSwjAMRWULDAE7tFBMC53QQw34/gcDzD7yIhtm8tZvNPrSB3CGcfzAETIBoVAUQpTKXvpADhVjqUrCU36t3miqFiO8wLQ7" +
            "9H7W67p4PaP7g+FoDCzd80z4zTEBpLzpbL5YrigvMOsIN1tG59hFXCLS3v7gklf5+niK4zN158vvb1oSPbje7o8keb4yKhagtHDI" +
            "yfl33gG0Df40EK2NAAAAAElFTkSuQmCC");
        D.put("dg",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAAAyVBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////9m7n84AAAAQnRSTlMAAQMKB2mux24LBqujAlmYQJL6R5z+0wUP3okEuK9Te0LJsbIjXVxlOLRylciz" +
            "N4zlkMuOntUJ/ZFVpkqH9SRYnRDedDZOAAAAxUlEQVQ4y+2SxxLCMAxEVw4k9N5D7733jv7/ozAZznJuXNiD7Rm/sVfSAp6ICGaR" +
            "0osykh/A+u4iFgjajh0KG0CKRPmjWEA0GUeCOZlKZzJZfZa4XL5QRKnMFYkjuFWuwY3UG03JICHc4raP3sFyuINurz8Yyu9pboSx" +
            "Lngi+7OmPKP5YplfGeuwgeKaN4a+bHm3h1U4HEVO4cR8vlxvvBA5kLvy5ra+ywPWl4/n6Dx+GQND8H40R1UpxJWfRMMX9NdfP9Ib" +
            "ifoSK3GGhowAAAAASUVORK5CYII=");
        D.put("dh",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAAAulBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///yHCTBAAAAPXRSTlMAAgMBDn6gR2meXxvjgsGvGNb8erOj2HwEtqV3gBMZFtrs3N70p9v27/D52YciJyS6drLV5MKwe5xGZ5te" +
            "qe5yoQAAAKNJREFUOMvtkscOwjAMQO2EXUYpBcKGllX23v7/3yL0AxIjcUHqO1g+PNmWbQA+KGQcEX6AhFQ6kwUpc/kCSENTcIpU" +
            "0l3LVDF7btWrAaLv1S1eg5qoVIvaVq+jE5/hdXv9gb3ekEZBGI5pYvGmNJtH0cLqLWm13mx3jPn2OjkwPN5ejrw9O6fP3fBMF5On" +
            "/+B6u+s/eDxfJu8LhMI4CkhI+G/evRgPh/VksagAAAAASUVORK5CYII=");
        D.put("d1x",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAABJlBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////////////////////////////////////////////cBqmhAAAAYXRSTlMAAQMCBS10PVmdkSpkmo0fEo7Y9G9SgBnpyg4r" +
            "8/1mufzOXvtUCVfsWDSe/goj6A3Pp9pjLARn2xTTeR3ekHv3IhPcmINp+CeM5Bwp5mXGxEh/LvZuanYQ8hiEOmGZJZY1V3G9tgAA" +
            "AQ9JREFUOMvtkldTAkEQhHt2DxMGxITh8PAEVAwYzqyoZ0Ax56z9//+Eq1VW+XCL+GgV/TAPW9/OdNcM8BeJks+iTdVKW6la2zmx" +
            "hkagqbklDmlta++I+qmR6Ex2sRvoIXvRl2L/gERwCoNDdN20MTbsZTBCf9S8RZnLxnJ5puFgjP74RCEzKWKJMTVtOCUzRc7OcR6W" +
            "wFovpAynAyzS5dKylUP8ixNJrJCrke5+clrW1gvc0Lo6Z/awyRLzW1J9rpLtHYZF7v7iz8Ee9w/KPDyyNNSoHHsn0NlT/wznnnth" +
            "SaJwecVr4Ia8lbsk7yvRkwUPj+ET8PxSfgVy4dt77RdkOcAgMJNUEMh3rauuf6kPf2Ek3eLYKAMAAAAASUVORK5CYII=");
        D.put("bt",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAABF1BMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////////////////////////jvJXZAAAAXXRSTlMAAQwcJSssKgsFKU9gZmttamERUW5oZ2UKWG88ZGk3YzsNXwkkXgImODo5" +
            "RmwGJ0VcPk0EA1ZxVAhbUy9dVXJJWkdMMx0eMjZ0QhojLgcQWUtXD3BQSChDP0QbYjT3e6UTAAACNElEQVQoz1VTbUPaQAzOIQIF" +
            "7nrovfBypYUOFFccUkSd4NBtsLE5p85tOv//71iuRcX7ALk8TfIkeQ7AHkLg5axdrJXZyG7m8tmNwuqe+p1iqUyZ67qcVkp5Z4UQ" +
            "2NoWTCqqtaZKMnd7a4VUawJdlHKOPwjXa4UUaQhjPV6z6dp/6osgSdRqe/i99sohNFxqQ2WlaoE3zF4077SgGyQxmu9YYDfJpHk7" +
            "gwl6NkaLPVvibZRGtKv9fUJsjHbf2SIlTKUTwBkcAASu0tEwBhgdYm1GDQLx4TiISeBpdnQMEJ9Ipd8zT3YQ8ManAEPOayGAM1Es" +
            "cHKdcbkaT88+ZOBUUz7DkTkdKXsOtM79wmhy4cDHSGp+gkC/LDHnJ4DP/eN5HxYMh8InmQSgmskAy3Wh24wShk8ANcqtZQkh8y/U" +
            "0HUAw892samtr5ybdUCraI7mEuDbDBtMAGSFfv4dwviyhmzDA9zMIKWrPW8Tfgxb2Hm7CJCXCV1nIKm/D60rGsZTzvIQ/pT8Chcy" +
            "upasCTdHtziS69si9C+EYVMkT35xNdjZdpWd7gLiO2F0VHKQ4l2kJZd2upe/CfkjsEH3nOAGG2sbJH+F3b9VA4Hi0wZbAHupTpCC" +
            "lZsvdSKGEBZR4lf+jV1t997m0tJ/yEWJenxxbwWHIRVmrDqNUlhYG/Yv1SiBjYngihptDMJcdB6e1Z7pzVDMol53mZr1MmvvAKrL" +
            "4uNeo/dYXFZf3ge8Ml/Z9n09n9TxH2lTRSrc6iIgAAAAAElFTkSuQmCC");
        D.put("btc",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAB71BMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////8dpsxcAAAApHRSTlMAAgEDJkdZYWNcTjIMIIDN8PbcmzxX4f78/fiJBVD7kBLS+u50yURdrSWmqCTR5BcI1qkE" +
            "OiPpqjVeOfmsT0l4Q+9Rz4rr40Y0LPKWViKEChGdUrlKdw20oVuZGB9ButpavacrD8JFWCEtFJFAG27QorsvC6+e2Ta17TP3PvFC" +
            "5uqOPS7efSqrVCfYZg4HHLgZxnGlvOi/YHMJBozAGn6YmpSGO/8EqS0AAAJISURBVCjPTZP5I1RRFMe/7z1Lpk3Cfa8yNUiMRFmi" +
            "QShpU0KbQkqlVCQtRNo37av27fyhfc+dKXPnhznnfM5dzjnfB+hyHPxfrjdvM+ylpKalL8gILVxk3UQOFi9Zmil2Lctanq2ReH5O" +
            "rsaMHxj9X7FylSUO8nwJG6PBQIzxjax2HBeuuyYi+cyXgsK14iv1/SJ4/K2zbliKURKxpi9RlztQauKg1MH6MiXGbCjnHRUbebSC" +
            "TahEiSUiVQTVNWIs2IzaVKQoMVJHsCXGvQFBPRoyG1FdJvS2EqTawnwFTdLcgpJtJizbCVq4s3WHhIN6tAUS7MSu3SJ7CPaKiYXa" +
            "o8I7mozs298R5e4DBDkiBS1AZ1YXuuUgDh0WHnWE4ChfG+45huMh9BaivE9diRJkiLap/wR7WonyAUbngWjtJ0+57uBpiZlkoHb+" +
            "GTpnI9qqJBBIK+ND53B+OF8vj8ZfxaSaC7iIxoJLQNsId4zaOpifW4X0UVYul8dwZVzkKkEnj7p2Hd3+DbT5MjGJ7P5Aegi6RW5O" +
            "IW+abb8lM5Oovc2j7hDc5bzr69ifYjRM9+LeffEDeUDwcMK2NyaPMPUYT57aecwSPMviAIzRCT7Hi5H4BItUVy85GKMTdPHqtZ25" +
            "NA+prN4kVPIWHe/iKjFdnoKh90GgNX5oH7dxnvoRlszR1g5PqFwMx9T3yVVhO5+/WOWyeb6vSvr6LaFqIDSQEDufPTJX8S9OPWIs" +
            "7fvwj5+/fs/+GUz6QJIsLs9F8vflOS6X43iJpL/akuLRdHfIGwAAAABJRU5ErkJggg==");
        D.put("air",
            "iVBORw0KGgoAAAANSUhEUgAAACcAAAAkCAMAAAA96dOpAAABnlBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////aMUgUAAAAiXRSTlMAAQIDGjMKJY/h+5Be5a0EiPz+TkOHSCALoP0FVe3qvC4IFrfTHVjvyZsvK84nc8b6+c2hbDsUPt0J" +
            "p/DVqA+AihK29vhyNmkeX+BC5lL1hlreoluvH8q4Zs8QI30mB2huQefFmWAxS9QwIZjzKZZJV9+raxfjcCqlpqlcLPEi0ImzKG3E" +
            "uxURUXKvROwAAAIxSURBVDjLrZT5VxMxEMcnCSpukZKogClaqFBUDqug4AEKRVFREBVUPNB6K9543+LB9792smXbLt0++3zmp+zm" +
            "874z851JiP7XElJUQVUFESmimjVr/xpS0Lra9V6MZFFflUfgH3UbANQXufxGKFUCS4o3AJ42GwucpE2bG5uU28YDYUnNW2Cth/qE" +
            "KARo2QpsS8ZaWtvyViipKLUdFh7aO0gEamnWt5wJOnfsrNvV7P/t6oY2Hrp7ClEF9fTuZsjL+OyevX39+/YPDDKmkewoKZbXgcaD" +
            "hwDtaW0c6x3uhDUWQ8OkSh1wH11H2pmwfKydruOPirCaIznZkfRolkWZMNoYjB3jiiLcd6LHaweRX0aPV+qudOSJk6fYDw6rJ4Sq" +
            "PB8JGjht/NAYp8pcnM5MQru4GlMVOQ50NsvE9CRbg3PlxUoh8uN2Pss9uDAzC6txUZGI0lKKLnlsbixBU467PBfiBF25mr523W3n" +
            "PY2xG+zaTcfnmkKBBfXxANy6feduLxuSu+fsmbvPW/0gXIh4+Mh3doG9mH3szgQ9cQP0dHUh9Ow5eFiMeZFiZ3x33LC8XMVxe2aG" +
            "wBlla3yMjxdfIYOGMj2O9dpaZN6sNF7SWzbwXbkvUtJ7Tn1iRUGKD2x3MvLif/xkkQ44+pwDvgxHGK3oK9Af3FCS32AXvpOMuPyL" +
            "08XMFS0BP1IionGS5nmUClyr/vkrqr8s+Hs5HpwIalsaoeoeq4jkgifxH56+8PoDKd16BAsOfCkAAAAASUVORK5CYII=");
    }

    /** key: see SysUi.KK mapping table */
    public static Drawable get(Resources res, String key) {
        String s = D.get(key);
        if (s == null) return null;
        byte[] d = Base64.decode(s, Base64.DEFAULT);
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inDensity = 320;                       // xhdpi source
        o.inTargetDensity = res.getDisplayMetrics().densityDpi;
        Bitmap b = BitmapFactory.decodeByteArray(d, 0, d.length, o);
        return new BitmapDrawable(res, b);
    }
}
