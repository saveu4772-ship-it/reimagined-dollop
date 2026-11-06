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
        D.put("b0",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAA3lBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////////////////////////////z/////////////9O//7OH86t/86t7/7eP/7d//VQD3Yw78YwT3YAT3" +
            "XwT3XwT8ZAT3YAX/AADyTAD1VQDzWAD0VgD5WAD0VgD/WwB/fwDKnWB/AAAASnRSTlMAAgEDTl5aW1QMWWxoaWALBAkIWGVkXREH" +
            "BSliZ2YzMHRqa3M8LW5jbTkubzovQ2NEYUJkcHBud0oDZ+zk6OXsiQEUMy4vLjIZArBVxaAAAADTSURBVCjP7ZNZc4IwFEYvsRAL" +
            "wRYktRXQBi1oV+niri1Wu/z/P2TuODgsM/W943n8ztzzlAAcQknJbERSOVE1SqmmVk+JQkjG6gYzTZMZtbP9dG7Ztm3VmSPh7KJx" +
            "adnWlexA09VlhHs+4jlUo7rbQtG+ZkKI3S4RgTA7XRQ3YSQj6e5jsNdHcXvH/TxOeH8U/0E8GG6Uxw36+KxqnlO44OEjiifPL4kB" +
            "ipgXSlEcPKN4eX0rMByNASYwnc0XeZbvHwAJrD7Xmzxf3z9//qVkUuYXtjoaWEe8oeUSAAAAAElFTkSuQmCC");
        D.put("c0",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAABklBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////////y8vLp6enr6+vu7u7o6Oj19fX6+vrb29uqqqrf39/U1NS2trbMzMzo6Oj8/Pz////8/Pz////5+fn19fXs7Oz19fX5" +
            "+fn////////////W1tb39/f39/fz8/P4+Pj6+vr////////8/Pz////////////////////7+/v////////////7+/v6+vrj4+Pf" +
            "39/Nzc3///////+qqqrh4eH8/Pz8/Pz5+fn///9/f3/k5OT6+vr29vbp6enw8PDm5ubq6urs7Oz4+Pj39/f8/Pz19fX////6+vrs" +
            "7Ozu7u7v7+/v7+/8/Pzy8vIAAADv7+/6+vri4uLx8fH8/Pz6+vr09PTx8fH8/Pz////////////////////////////HHIZgAAAA" +
            "hnRSTlMAAQ48PjoKAgMbb25yaxMSZGNmYAU9QD9IZ2VFQjhsbXNhCWJpWVppamlpZ2drTwYIBgcFF2BqaDFWZjZuXSdoThNnbS1K" +
            "aRoHaV8ZFFFLTUxNSVJsSllTBAYDVmxlVxECVm0+VGdUVlRuImpPCDxTbBBUbT8BU2YbTG5oYmJrWHB0KistJSsTdTMAAAGaSURB" +
            "VCjPjZNnV8IwFEATRYtUNIkDG7XKcC+KAzcqDtxbEbfi3ntv/d+mLaUtejzeT827yctL8gpADKgB/klSsoolJTUe4zjOyqXZFPh0" +
            "ewYbcXrCTKSASVZ8RXZOrsNhySMqAs23OCwFhdkApNptPM+LREPk+aJiJ1vnclOWg8YFpQh7SlxMYEEdx5C/7aW6KCuvUKisqjYK" +
            "VFNb55Ukqc5X39BIaFNcUH9zrEhvi58aU7VK6mFBWzsyphI6rMpNgs4AMm0e6ILK/O4ec1U02AtkA/v6WSKjEAaYgKF8URgMeAIG" +
            "MTTMXgqMjI6NT0xOTRNNoJlZJZP8jBz0zfm1c6DgfHjBuqAUBiKLS1ivajlryL2yCuEalNY3sPGAAmnaZOWCSHQJmy+RbIXYFtI2" +
            "i5uFsLMGQWRXjpsE3tsHnBRV4kZBDw6P2HwRk0ThOQa+E3W+SaDTM+78ApNEQWcur65FTH6Km9u7e0x+CPrw+PSsN5AmnKyvbihG" +
            "Otjz4pI78ZU1M28zwL+5WSeG3z8Sef/8Cv/1z8DfAN8e11sZb4OJLAAAAABJRU5ErkJggg==");
        D.put("b15",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAABGlBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////+fn/+vX/+vf/+vf/+vf/+vX/+vb//////3/3mGP9pnP4n236oW36oW7/pXH5ll//" +
            "VQD2VgD2VgD1Xgn/YwL2XgL2XgL2XgL3XgH1XQb4XAD6XgT8XwD3XQD3XQD3XQD8YAD1XAD/AAD/XQD0WAD5XAD5WgD0WwB/fwD/" +
            "XwD+WQD/WgD/WQD/YwD2XQD6XgD3XQCbw0lKAAAAVnRSTlMAAgEDTl5aW1QMWWxoaWALBAkIWGVkXREHBSliZ2YzMHRqa3M8LW5j" +
            "bTkubzovcWZoZ3A7NAJIqqGjoaldA3OWbPv3+/iMcJJn8+ru6/KMARMxLC0ZAsi9lCgAAADnSURBVCjPY2AgBBhhAEmMCQiYWVjZ" +
            "2NnZ2Vg5OJkYmZiQZLm4eXh5eXm4+fjhQgKCQkJCgsI8IkAgyiMmLiEoJCgJNIdBSpoLaIiojCwIyIiws7FzScuBJOQVeBQVFSHi" +
            "QKCopMirrAKSUFVTBxoCE5cFGaihCZLQ0haVRQUiajqjEkNIQldP3wAVGBoZg1OJKCiVIAM2dRMGBlMGM3MLS1RgZW3DwGDLYBcW" +
            "HoEKIsPsQRIOjk7OqMDF0RUk4RYVHYMKYqPcQRIenl7eqMDH14+BwZ8hIDAoGBUEBYbgzUv+tpgglAEAEgFnK1R4M3IAAAAASUVO" +
            "RK5CYII=");
        D.put("c15",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAABrVBMVEUAAAD/////////////////////////////////////////" +
            "///////////////////////////////////////////////////////////////////////////////////8/Pz////////w8PDp" +
            "6enr6+vu7u7t7e3///+5ubnU1NTf39/l5eX7+/v8/Pz09PR/f3/f39/z8/P////8/Pz8/Pzz8/PV1dX////29vb////29vbw8PDz" +
            "8/P////////////8/PzX19f09PT19fX////t7e3t7e35+fn////////////5+fn6+vr////////8/Pz09PTMzMz/////////////" +
            "///////////z8/P////6+vr39/f19fX8/Pzr6+v////////6+vrg4OD////////39/f////g4OD////////////////7+/v19fXR" +
            "0dHv7+/////////////////9/f3////U1NTq6ur////////////////////////q6urr6+v////////////t7e3////9/f3/////" +
            "///////////////9/f3////5+fmBQf1tAAAAjHRSTlMAAShAPCQCA0p2bm9Da2NkOSNEPj9ZaWZWHz1tNThsZWYwMWdqaWloZwsG" +
            "CApFaV8CIG0RXmdwHwlYaB41bl4VJ2xOGGlqSB1VTE1HYmcgLmVkBQQsYmBfL2sSakIbaFwNOms7NmFiBTJCf3V3gDVJhHSM/Pj7" +
            "8kii+/l5jv3+yK16jfrkiv54mYQ7ModJjwIAAAHGSURBVCjPjZNpV9NAFIZvbJmSFBo2E6HAUNa4oCIgMIqsZd8LFQV3UNkEaQtW" +
            "oAoIKhJ+M3eGMaQcDofn5MPMfTJvJncSAIkigetyw+MVeDKckkII8ZFMVfMjmpqFE0LOEz3ZAR0JZOc4pdy8/IKC/JuGKTBu8Vle" +
            "IYoiNahpmqyjwUlQLUZRUspD6H9RRjGwNIQiRzsrUgkfauWOoBWVVZzqqpo0YVXcviO4e6/2PnWvePBQbrLuke5aQQ2v6JIC9Q2N" +
            "7qjA4yYgvAnNLYy6BS17gnfj9bRVT9/VszasKtBu6fgi50Lv6MQkBbq6LcZY2BE6DfegINDbl9nfPzA4ZAalMIdHnGMkMNo4JleM" +
            "WeMTkUjExxWBSeZ+wano8ymveM60QdNawswXL4HnGPRCE2dmeT1kXOyuzl5BBEbD4ghcgr5+81aBUFgejRDv/KZlme8/ANZxxDH9" +
            "XMwZ0fn56MdP8FnHgSBqTKNYWFxaXlr5AqtrX5cl6iL/iNZj8UR8Y/PbcTyRlHyPbaHYtndS9o+fdsp2SNm7KPbsmL3/6yTmEjtC" +
            "HCTjycThvpPDo5K/UfyxL+EviiMmd++C/bvqlyG+SyBwCvxDf+0TpHsPAAAAAElFTkSuQmCC");
        D.put("b28",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAA8FBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////////////////////////////////////////////9/f3/////////////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////////////////+EGRHIAAAAT3RSTlMAAQMCNGJa" +
            "W2AgOnJnaWhwIwgJBjttZGVrJgcRXVlmX00EFGxqBRNVVliepaKjoaeF/dQNMPD8+/nJDDHy/svKMvbO5e/t7uvxvyouLSwkmDfH" +
            "TwAAAPVJREFUKM/t02lTgkAYwPFdVpe0XXQ9kPJICzu88cBKsfLIbr7/t+lhx2uHmZpxfOnvHc8fnoEBEPoP1oik4Z0ZxgRHolQH" +
            "9CQGB3inxk8ZB0YiuRkRIUQqncmaOWBaZ+mUEOewB+VpgerFKCtJ7KII+8oVCJf8ituWUVrhls0TtArh2mTBlnUI9tk3MuSMzXTV" +
            "rNtjOFRgNjMULHsXhLxekN/BFrzBKiIoVqs3mopWuyOQhiKOH9LtQegP3KGruHceHiGMxr6nnu/6kz3DwHMdxdB7CsLzS/iupvKK" +
            "2Xzxqli+vffgycXH55fqu/9D/v6Xwgj6BXcfZxKQcpzAAAAAAElFTkSuQmCC");
        D.put("c28",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAB3VBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////////////////////y8vLp6enr6+vu7u7m5ub19fX6+vrU1NS/v7+qqqq2trZ/f3/i4uLr6+v8/Pzy8vLx8fH////r6+t/" +
            "f3/v7+/8/Pz////5+fn19fX8/Pzw8PD////8/Pzv7+/Ozs6qqqrs7Oz09PT////7+/v8/Pz19fX////////////////////8/Pz6" +
            "+vra2tr////y8vL////////39/f8/Pz8/Pz////8/Pzt7e3////////39/f6+vrh4eH////8/Pzy8vL6+vr////////////09PT2" +
            "9vb8/Pz8/Pz////////////////////////19fXS0tL9/f3////////////////z8/P////////////////////////////4+Pj/" +
            "///////////9/f3////9/f3////7+/v////////y8vL////9/f3y8vL////x8fH////9/f38/Pz////////////9/f1392cMAAAA" +
            "nXRSTlMAARY/PDQFAgMpdG5vc2AKIGljZGhVD0E+TWZhQzAZcmtnbWx1F2VqTk9namlpZ2g/BggGBwQbamVmE1pnAlNpUy1rbiQl" +
            "aHIlA2EZCUtsTQZUS0xHWGxFIWM9BGRnZF9jVgwjZWo9ImZlZ1AVWTIdWVpGKru6ube/Lra4wIw73Bv1xDf7/fx19vo4/v7mEE/0" +
            "vXtE43nu6Pn7/vjLXFO9nQAAAddJREFUKM+NkwlT1DAUgF+tkmoVYRNkCQqG1QCKx3oroqICXiAIKrIcHniL5xZvUWkBZVW8UNT4" +
            "W32p3bYo4/h1JtP3viR9yZsCBBh54D+ZZ873MRcUhDlCiEUWLrIXI/aSwqUYkWjDouIERRKsJMwtKzVNM1nGuA8rL02ayeUrUFRU" +
            "2shKHiCqbDu1qgTFaqb3kHnBpaS0ukaL6iAO4PikakMh16yt81m3foOMCck3pjel0+nNW7Zu2y53xFbs3JUvsl5wGgm6u+H3jcGe" +
            "vbO+IfZZOt8Ajfspjwl64CAQPb+pWdcdiUTLIS3g8BE+u1xJj+J041hrm2CsWIRCth1vJ9iiio4TnZ01XSdDQU+dBiNoI4HuM9EK" +
            "0ZNptzLEr7e7ty92jv7CgbPnzuuOXxjsi59cCH7xEvYSLvdfCatKMQ2/WgAWNF1jWJeouo7ixkA9MtR708jArdt3hnRQfhfFvazj" +
            "OMP3Hzwk0PUoO4yB8/jJUxTPlM/Ic6h9ofK8RDHqep439nMcOiaUfkVeeVkUrz2lJnNv3r6bUl4wf0y9D4SrPnycCPORmMx9+jwd" +
            "5SORU1+mcfhDjH513ZlvHo4h310HRY+agx/6SsRfcFb2r1/GsOYCfgEOjqxXTMbS+gAAAABJRU5ErkJggg==");
        D.put("b43",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAA51BMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////9aq4iyAAAATHRSTlMAAgEDTl5aW1QMWWxoaWAE" +
            "CQhXZWRdEgcFKGJnZjMvdGprczwsbmNtOS1vOi5xcDs0Rqqho6mY/fj8+Y/7kf76kJTz6u7r8o4TMjEZV33GvgAAAOxJREFUKM/l" +
            "02tTgkAUgOFlkd2URSHRVDZdFCxvXdQi07yWl+L//5721Niw2eTXZno+npc5MxwGhI7R9hIzLOkpg1BKiXGSxhrGiZoxmWVZzMym" +
            "v0Y523Ec+5TlJZcVime2Y5fkHlSuZOQS1+PA45RQUjmHUK0xIcTnXBK+sOoNCEHYlEv2cw4LLy4htNouV+XDzr8I3R5nKu/q+uO6" +
            "Llw3iTRv5CdFt/3BUDW4MxDSUS8+dA/Bjx5GqsdoDMGMJ9+eH8VPfzf401mkmkznEMIf3nwBgS9Xz6qX9Qauu90FLVXw+vbrv4T1" +
            "Qxp6Byeebua5Z4dcAAAAAElFTkSuQmCC");
        D.put("c43",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAB6VBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////////////////y8vLp6enr6+vu7u7o6Oj19fX8/Pzh4eHU1NTf39+qqqq2trbMzMzz8/P////////////5+fn39/fw8PDz" +
            "8/P8/Pz////////w8PD39/fz8/P4+Pj39/f////8/Pz////////////7+/v////8/Pz////////////////5+fni4uLW1tb5+fn/" +
            "///////////////////////7+/ve3t7////////////z8/Ps7Ozs7Oz9/f3////9/f3////////////9/f2/v7/29vb19fX/////" +
            "///////////////////i4uL////y8vL19fX////////////w8PD29vb19fX////19fX29vb39/f8/Pz6+vr39/f4+Pj////z8/P3" +
            "9/fh4eH39/f4+Pj39/f6+vr6+vr////5+fn////39/f4+Pj4+Pj29vb////////8/Pz////////////////////////////////5" +
            "+fn+/v5Sx6mMAAAAoHRSTlMAAQ08PToKAgMabm9xbBQRY2RmYQsFQD8+R2dlRkI5BghpbXNoW2JqXGlqaWlnZ2tOBggGBwUWXzEH" +
            "VWU1blwmTxJtLkpqRGkbUUtNTFVeVVpXXz9MXFIJELnCvcieQUlNFzeyxbzHpRb+9AQ65+AcFfP9/L0EPOr71vVHPOjY0D3rcz7p" +
            "5yBA7KJE6vrtqDfr74zl6+rx+v351BkX6B1YhK9wxAAAAdBJREFUKM9jYIACRhhgIBIwMUMACysbXIyRnZ2DnZOLGwS4eHj5gDx2" +
            "hIH8AoIgICAkLAITEmURExeXkBSCAClpGQlxCVk5eQYGBUVuLi4uXiEY4OXiUlIWAOpTEJYGmiENl5CWFhRQUQU6QUFNCsKHAhBb" +
            "XQMhoamlDQY6unrIEoL6BoZGhoaGxiamZubIEtIqFlBHWlqpSCMbZW0IFmZnsLEVRNYhZccBDgQGexVBFMtVHBjB6h31UF0l7eTM" +
            "AJJhdHEFGoQsIcUJlGCUd+MVcldRVIFKSKsrqqh5sHNwMPB5enn7OPj4uiv5ASUs/QMCg4Lh0Qh0REioRlg4A0NEZFR0TGycT3xC" +
            "ItA4Doak5JTUtHQ+BoaMBSCQuWBBlgUDRzZDTi6Im5fPwFBQWFRclLmgeEGJZXYpQ1n5goqiiqJKTqBEFUh5ZubCagZ5hpraBUUL" +
            "gKgOLlG0oJ6hlKGhEagTTaKoiYOhuQWkHkWiaEFrG0N7B1g9ikTxgk6Grm6IemSJzEU9Gc29UPXIEsUL+vonwNQjSRRlTpw0Ga4e" +
            "WWLBlKkI9XCJaRXTK2ZkzqxAgJkVs2YzMMxZgAXMncfAEC4ohQFU5ovgyzMc2AADALKGxOT9A2VaAAAAAElFTkSuQmCC");
        D.put("b57",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAAzFBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "//////////////////////////86kuEIAAAAQ3RSTlMAAQIDGV1bWmE9GmtpaHBFBwkIBh5nZmVkbEZZYmAYUnJqbxtOT21RfKii" +
            "o6Ypx0O8/fr8P75Avf7CQbfx7O4hLy0L8poUggAAAMpJREFUKM/l09cOgjAUgGFaRaYDtLhH3Vtx7/n+72RP0aDF6KUXfnftH07S" +
            "NkjSFwh7QvhpDyGMwnIEKKrGFgj5VTdMEI3F/b2EZVvJFHHSTCYbZ0vb4gNzaj6Sl4nDkYLCJhZLEMoVkxqmc0cMSmm1BqGezbAh" +
            "j8AnUh1Co+nv3hm/D60qiQqaPOT4YV94J293uj1BfwC3PrwGjSCM3clU4M4gzK9u4IvFf4flauqKeFi/ud0NhO1ufxBp8FDH0zng" +
            "8vFnwqEgJN0AHo9wj3vVTE8AAAAASUVORK5CYII=");
        D.put("c57",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAB3VBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////8/Pz////////////19fXp6enr6+vu7u7o6Oj19fX7+/vU1NTf39+qqqq2trZ/f3/i4uLr6+vy8vLy8vL////r6+t/f3/y" +
            "8vL8/Pz////8/Pz////5+fn19fX////////////////////8/Pz39/f////////////////////9/f3////39/fi4uLo6Oj09PT/" +
            "///////////////////////6+vro6Oj////////////////////8/Pz////////////////9/f3////q6ur9/f3////x8fH/////" +
            "///39/f////7+/v////////9/f3////4+Pj////7+/v8/Pzy8vL////9/f39/f35+fn////7+/v9/f39/f3////////X19f/////" +
            "///y8vL////6+vr////n5+fw8PDr6+v////9/f3y8vL////y8vL////////9/f38/Pz////////////////////+/v5rjjSkAAAA" +
            "nXRSTlMAARU/PDQFAgModG5vc2EKH2ljZGhWDkE+TGZCQzEYcmtnbWwWZWVqTk9namlpZ2hABggGBwQbamUUWmcCUmkXalQwblEU" +
            "XllXYSBYX0YpwMC8zz8uu72+v8qQODjOxTb7/frhBMG2t7up5f38twhF6pZHUlDw/ukhSvGTSfH+8zJM8qtL+UdN89Tyd/T9EYD2" +
            "5Hjt6O41+/74uzrMnBhc3AAAAdNJREFUKM+Nk4lT00AUh1+NdleCSGiAGhEsEKgIasGzHuAZFRTxKIKgCZRL4wUab0QF660o4Pn8" +
            "W31Jc6GMw7czO7O/b9/bnZ0EwCXiActkhbDSQVgV9TPGGGerC8RCQlxTtJZWLGhYLJXEiBK5lHlRWbkgCPF1suIgF62PC/GKDSQq" +
            "q0Rio+KSqBbFmoJSErWy3UP1hKLWxWL1SVvU59ceCo2aTb5QGzY3OjRt2aqGhKpsSzWnUqmW7Tt27lLrQhW793iXTCfCrWJ79+Vf" +
            "DPYf8M5orZYkqe0gt3MeOXQ4IUly4RFaHD2macdPnARm769Mt2ua1t5xisTpzs4zXWfPASd1/kIm0525mOnpJXEJid991IaX9aPL" +
            "ZRJXdGPAGCxm2Sykh4ZHRkbHrl4zumxhoInXnTe9YZ8PN28Z2J0XBt4en0jeqaXTszB+Fwc8gWjRuHcfGIcHD9EwA4FU9YjzKDx+" +
            "gnbrQExaT6cgCs+eU75ImPiC8mknDwvLnHnJIfcK3V2+MPF1KyTf5PNFFfgWJt65eUhY1vsPOT8PCR0/Vnzy80BMWrOfvwR5ICyc" +
            "m6fpL7Ewo+tfvxk0+3zXf5D4iUvwy/4YEv+gyLn//TIRvhTwBxf80Qt+9TJgAAAAAElFTkSuQmCC");
        D.put("b71",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAA51BMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////////////////////////////////////////////8/Pz/////////////////////////////////////////////////" +
            "//////////////////////////////////////////////////////////////8f8v8TAAAATHRSTlMAAgEDTV5aW1QMWG1oaWxg" +
            "CQhWZWQSBwUnYllnZjQtdGprcz4qbmM6K287LHA8YTl0cj3p4eXi6IiX/vn9+pL7/JOW8+ru6/KPMjEZWtFxWQAAAPFJREFUKM/t" +
            "k9lSwjAYRtOUNgIJtLYspRGCCAplUxS0yqIsovb9n8f8MDgEGLjxknP5neTLZEPoFNqGrQxL9JhhEkJM4yKONYy3bCJJGWM0lY7/" +
            "Rbpl27Z1yRyJS41M1rKtnOxBea8gSxyfAz4nJil4VyCKJSqEWOcSURbsugLiplqTJZucQ2HyFsRd3eUqTrVxVARNx1fh9dZqjXK7" +
            "puKJzmpG12cq9P5Bbg+le49PKv3BM4hUtM8LCBa+vqkMwxEIEY13xk+i97P4J8HC4c6xj9fHfuiiPkC409lcZfG5hMeQ+QqKKsH3" +
            "z9G/hPV9NPQL786J6S6GnkkAAAAASUVORK5CYII=");
        D.put("c71",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAB2lBMVEUAAAD/////////////////////////////////////////" +
            "///////////////////////////////////////////////////////////////////////////////8/Pz/////////////////" +
            "///////////////////w8PDp6enr6+vr6+vq6ur////////////////////////6+vrQ0NDU1NS2trbf39/////29vb/////////" +
            "///////////////////i4uJ/f3/v7+/8/Pz////////////////6+voAAADq6urr6+v////////4+Pj/////////////////////" +
            "///T09O/v7/l5eX////////////z8/P////4+Pj8/PzW1tb////5+fn////////////9/f39/f3////6+vr6+vrIyMj8/Pz8/Pz/" +
            "///////////19fXT09P9/f3r6+vMzMx/f3/Q0ND5+fn////c3Nz////////e3t7X19f////i4uLz8/Po6Ojn5+fh4eHv7+/////l" +
            "5eX7+/v////6+vrp6ena2trn5+ft7e3o6Ojt7e3////////9/f39/f3////////////////////+/v739/f5+fmOLJTBAAAAmnRS" +
            "TlMAASdAPDslAgNIdm5vRD5rY2Q6Ij9YaWZXIG03NmxlZjI4cGdoNDNeX2dqaWpjMEGCeHl6dgsGBwgEWH2DiPrz9vXrCHPx9PmL" +
            "/rcBJf2B+5IWzfx+if1MBLJ/6yjcGkv6uAeRv7a5uryt72eD/HNNT1M4e/2eBQKE/kuTvgquZwis2BcLr4ULrfMMoa5Mosis5Yb4" +
            "+/6Uijk1y+FpqAAAAdtJREFUKM+Nk4lT00AUh1+5tja2ICJRlIq0SL3QIlQBDRSQuiquGo3gLQIqgme1Hoii4n2gIMpb+V9926ZJ" +
            "BIbxm8lM3u/Le5vdTABsfDbwvxQUFimKC0ucyMcY87NVAW01oQWCVDDmTgyVlq0hykrLnWhtMFRREVpXqWepXK+q4AYSxYEqTdPs" +
            "nAwVVYGNJDZVqyHhvNgcpoHVNSS21EaihB7OoVMVqa0jsVWvjxHbtu/IsnNXLFavN5DYvSfe2Li3qTmh2Le/pbUtHj9QROKg0d6R" +
            "7Ojsym0Kug8lkz2pNro/jBwRjxxVe2JwrBcVx1UHF1zgCXoYfP6Tp9Dk3OSnSZxBU5rWWehTDf1o0vMWnssJC89foJRB6x/Dsizp" +
            "ERcvAWNw+Up2AbdD4NWBhJ/5rg0ODV+/cXOEux23si/L6OqD0TEjL3Ds9p279+6rfSTgAVppR8g0zX0IjzIZeIwp7qxB60l88hRa" +
            "YOAZColekebjGQbPJ1Bw9Aop8AXAy0k0JP4jTP7qNbyZUnMWCXwL797buUdI+aHhY38+9wgTP8FnJ3cFN5Nfvrq593S/TdPh4xKB" +
            "4vuCJ7fFDzEjZn8as8IlJdQ3n8Nl+EXid1SPLCY6v9Ivw/zLwOAvEeLaqxPXWkkAAAAASUVORK5CYII=");
        D.put("b85",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAA2FBMVEUAAAD/////////////////////////////////////////" +
            "///////////////////////////////////////////////////////////////////////////////////////////8/Pz8/Pz/" +
            "///////////////////8/Pz////////////9/f39/f3////9/f3/////////////////////////////////////////////////" +
            "///////////////////////////////////////////KRAJfAAAAR3RSTlMAAQIDF11bWmE+GGtqaWhwRgYJCB1nZmVkbAdZXmJg" +
            "GVJza2lxS2MbUGhuHKzo4+XmPcFEu/77/UK8/EHAQ7Xx7O5AIC8tCxvXz+gAAADDSURBVCjP7dPXDoIwFIBhiopVBFlFEcGFeyvu" +
            "vd//jexREw01em3id9f+OU0TCsd9gfi7EP+yhxCPwhEBRHGMLhB61riYkCg5qTz3VE3XVIOYKSptKRlN17XbgQq2BTtLzBviROmJ" +
            "bg5CvlC0LJE8lCzKMyCUZRdjp/LgYIpUIdS8uhTQaMJtW+1ON6DXh4nBhTWE4I/Gk6AphNllwkz8wy8FfzRnPu0CwvLNY1jB81lv" +
            "trugPUwcjifG+ePPxIdYiLsCOKGS+oCTUfgAAAAASUVORK5CYII=");
        D.put("c85",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAABuVBMVEUAAAD/////////////////////////////////////////" +
            "////////////////////////////////////////////////////////////////////////////////////////////////////" +
            "///////////////////////////////////////////////////////39/fu7u7w8PDx8fHw8PDx8fH4+Pj////9/f3MzMzIyMjM" +
            "zMyqqqrY2Nj////////////////y8vL////w8PD////39/fy8vLU1NTg4OD////////7+/vr6+vp6en29vb9/f3////5+fn////n" +
            "5+f9/f3////////////////9/f3////r6+vy8vL////39/f////7+/v////////////7+/v8/Pz4+Pj////7+/v09PT////9/f39" +
            "/f36+vr////////8/Pz9/f39/f3////b29v7+/v////09PT////9/f36+vr9/f3o6Ojw8PD////t7e3////09PT////y8vL////9" +
            "/f37+/v////////////////////////09PT+/v7r6+tj1bMWAAAAj3RSTlMAARU/PD41BQIDJ3RvbnNhCx5oZGNXDkRCQU9raWpl" +
            "RjQUXllaYF9nTb6/vMiQNsf7/fnz8/L08P36gQoODwZrwP5iHeL8NRDF0waVp1D6ZjDs/PUyDc7kIQS3ub2r57gH6ZdHUk4WRvDq" +
            "IkmUSPD+8zNK8q3x+UdN8tUY8nn0/RJMgOR47eju+/74vTjOZn9URigAAAGuSURBVCjPjZNpVxMxFIbvANIEkEJR2qKWShFoFZmg" +
            "oqWiBBz2RWXRAsoudUUECq64oCyK1/KLuTNtZ5Eej8+HnJP3SSZz70kAMihZ4D/Jyy8wOFHoMjPGGGdFxSU6J0vdZTRj1gfLPRU6" +
            "pzynWTaq9Pp8Pn/VmbMG5wJev89fHSRxPuAmakIZai+43XX1DSSKwpFwOBwxocnFS410yGXMQROJoCp0mq9c1bnWcv2GiIqILgSt" +
            "ENgauxkj2m7d/iNUbM8KiR2Zn+y8g0IzRQoPuxSqSmHdPbTbEgJ7jeV90I8qOsSA3gQOg0OatImUisOUMrh7T9wXQppC4sgocIWP" +
            "jaerMIWGDx6yeBxC7ROTk1OPHktLTBs9nTHO8c6KrBA4N7+wOPgEFIjDUgKj9gIlPn0GjMPzhL1AoykvOHfBy1eOAhGX5esVcMGb" +
            "VcodQsM1PV/Xc7uQWnKDw+ZbFOgUGr57Dw0f0rljB36EhU+Z3CZkauvzFzO3CRW/+kfM3BLL8tv2dytHW3d/7NDwl1hJqurunqDR" +
            "ZF/9SeJXrnt1QCKv/hi/PYv/ejIKzwUcAUBmz3CKVIESAAAAAElFTkSuQmCC");
        D.put("b100",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAAAz1BMVEUAAAD////////////////////9/f39/f39/f39/f39/f39" +
            "/f39/f339/f////9/f39/f3////////////////////////9/f3////9/f39/f39/f38/Pz////////////////////////9/f39" +
            "/f38/Pz9/f39/f3////9/f3////////9/f3////////9/f39/f39/f3////9/f39/f39/f3////9/f3////////9/f39/f39/f3/" +
            "///////5+fn////////5+fn////+/v6IeF2IAAAAQ3RSTlMABQcGAQK85eDh3+TLIgTY6iIIGRUWGNT+/f785TETDFja09f4/NrV" +
            "2YQDbJtn/fn6k2j7lZRqmGLz7uvykRIyLC0xxdBwYAAAALNJREFUKM/t01kOgjAQgOFRqYobKmvBHVcURHFf0XL/M0lTxTQmegG/" +
            "t86fzFMH4JdUmskIyUhAsUw2lxdFsVAslVEFoXcFiTDVWjKqy4qqaLqBTdO0jEazpalaGwBBp9uzbbsfMYPhSNLHEwAHpmzHM7DH" +
            "jAbXm2OMoxdsYd9b0LAkQcSzyOof/oELruFjXmCsadiQ5y9LRGRLw25fPfCOp3N8BHC5hjdeeJe/3pLgfELwALUVnaGAJhwUAAAA" +
            "AElFTkSuQmCC");
        D.put("c100",
            "iVBORw0KGgoAAAANSUhEUgAAABgAAAAkCAMAAACkEQocAAABjFBMVEUAAAD/////////////////////////////////////////" +
            "///////////////////////////////////////////////////////////////////////////////////////////////z8/Pv" +
            "7+/w8PDx8fHu7u7x8fH8/Pz39/fMzMzU1NTQ0NDa2trn5+f6+vrj4+MAAADt7e38/PwAAACqqqrj4+Pq6ur29vb////////W1ta/" +
            "v7/l5eX////////29vb09PT////4+Pj8/Pza2tr////U1NT7+/v////////9/f3////5+fn6+vri4uL9/f39/f39/f3/////////" +
            "///////19fXR0dHt7e3MzMx/f3/Ozs75+fn////a2tr////////d3d3c3Nz////h4eH09PTf39/e3t7v7+/////k5OT7+/v////j" +
            "4+P7+/vm5ubOzs7l5eXu7u7h4eHt7e3////////9/f3////////////////////////+/v739/f7+/v9/f2V0rNZAAAAf3RSTlMA" +
            "AQJlkYyOYgQDBby2o/z5nVSmn5zg/t2bUZCKhPv9+n+Ggf319fT18vxBCgwLDguc8wJztgEDJf2VFstMBLHsKVfdGkr5uAcGkL+5" +
            "vKrvaXT7/fx0TU9TOHueBQKE/kyTwAquZwir2Bivhgut8zCxqK41nsuV5IP4/jhoZmc1NVAjMQAAAZ9JREFUKM+Nk2lX00AUhm+G" +
            "VpMIk9rIIOpMXSggFQQU3EDArUhlaVVcKKhl1bK4o7LcAf84k6ZmOzkcn3Py5X0mdyZ3bgBcNI3U0DT4TxoSyROKZOKkFxHdME39" +
            "FNZp1E3T0Imnm6hFFRZNedHpdMq2zzQjc9YzbDlr26l0qxLnMIbzSlzgThEmmYvglGb4RSUuIY2uF3jZF1fasor2bEdnWFztyl3L" +
            "5XLdPdd7UfiCYV9//ZA3hGC+EHiTaAMaDMKt22qVLzjeAdU9zbh7zynsCUnFEAyr7sD92oaeEDgyqlICYwcZIYQMiAcPgRB49Djy" +
            "HRyf5McNoj2daCwUnk1OMf+NafcG1GPATLHkner5i5ezr147NzwOb1D4QpZU3bcwVy7DPFos2BIpF96BDvn3yGWoVyX2oUwGKovI" +
            "WaiJkuMSwPIKZmS47ZStrkHlo1MnIvATVNfreUBI3Nisbv3LA4LiZ/ji5b5g1Pr6zd03LAR+/3FIZcww8J9/hYxOyTb/xYu/rSL3" +
            "sWpzlYibxKQSf3Z296Ls7B/3yxAjBgJH8NnBMaWvNxUAAAAASUVORK5CYII=");
        D.put("qs_air",
            "iVBORw0KGgoAAAANSUhEUgAAAEgAAABHCAYAAACkuwGSAAASSUlEQVR4nOWcWZCc1XXHf6enZzQajTTSjBa0L4ClEMIigbAA4xgM" +
            "FjbYBkIMdhKn7MriVJZyUnnL9pKHPKQqVa5KHhI7trNQpsCAwWCwMGA2gRQkgSSWQSA0IwlJI82MZtHsJw//c9WfWt0z3TMjqCi3" +
            "qmta3d/33XP/9yz/c85twf+z4e5WzfVVXfx/bbh7DTBmZl70eQ5YCDQDs4AZCIse4Chw3MwG4TwGKDTlAqATGDQzd/c8MA/4FeBG" +
            "YD2wGGgCxoBW4Dngl8AuMxvMfxzCfxQjAOkBhuN9HfBJ4FvA5QiYuXH5AHASuBCoR5p1EDh43gIEYGa9AO6+DLgVuB34HNALbAd2" +
            "Ae8BdUjbfg24GBgCGgDOS4Dc3ZLfcff1wG8gk5oNPArsBV4DXjWzQ+4+B5ldD7AEWMv5BlD4l1E4bV6NwLXA7wDXI615HPhvoB0Y" +
            "BHrDVw0D+4Bl8YylnG8AIb9x0swGQiNuBL4GbAL6gSeBHwBvm9lw9kZ3rzWzjgBrOZngdT4BdMLMRiK0bwb+Ai32HeAJ4CWgtxic" +
            "GD3u3gCsQP7oIAK1MoAC2SwlOM0rijnGxzHC54yEmW0CvgysA3YDPwIeA44Ds7L+qWisBi5D69yGotrEAGUeWBaIcuz0owAvyefu" +
            "s5Azvgst9hng34EXzawjLu8f5/5m4BMIk93IZ00MUNw8D7gEyAGn0G50Ioc2UEZtz8mIzZiNFuAh32Lg14HfA34VacAPzeyRdE+p" +
            "zUob6+4rgFsQQIeA/6ESDQp7ngXcBPwV0AgcCwF2oh3pcveDQBcwghjpMAJyGGmemdlIVqgpaFcdCsX7wyEvBH4Tac8K4EXgIWBn" +
            "uVQjyRHgNiBg/wSYCdwHvGRmg+5uE2nQQuCzwG1o1+YhBnpRfOYoXPagHe1G4fIoCqWHEXAn3f0E0GNmoyFgM8qBHIF6CuhP35cb" +
            "IXirmY26+yLgXuDrwHzEbR5BDvlQuWe5e87MxsJnrQWuQJv7IvAyYtZACQ2KHa4BFiCH9znEMNuAHwYAixFtvwShPgvtbB7YiDSr" +
            "A5lifyx+H7Dd3XfG57kAJu3uTCDv7t3xmVHG7wU4C1EYvyfkeRV4OMA5aGYDpe519xbkrPtQlPttlJMdRuBuOZ2omvlZAIXazQRu" +
            "RtpzMXJaD6CQeQSZzpNIk+YEmM1IwxYALSjPWRqfNyItew/4AIXRtxClTxGyG1H/4UgRxgsKCwKcr6JE82fIKb8CtJtZX5n7ciFP" +
            "Hcq5rkCW0IQi3cNmdjTWP3AaIHevjWfkYnGbgDtR2HsDac7jAcgSRLZeB16P+2fGfQ0x2WJgFfIJC+KzpfG6FGnoAWSeSWOPIb7y" +
            "rrsfQybbF8AOxKsfmf0fIp8zO+75HvA+cNTMhopAqUeaPZABvSnk2xwybQXuN7MPMhZkgOfDkTXF4hqBTyOntRzYD/wYeAqoRZrT" +
            "AYxmQ7uZnQpHTWbBNQG4I/tegij/emT3K0PwsczGfB2ZY1+8OmLhJ+J9G7AG+DNkko8D9wNbx4mkzUjLD6PI1AhcDXweuBJZx3eA" +
            "FzLRrvf02jJIr0Gh7mvIj7QBf42Su75MPSUfk4yaWWcZoc4aod4rkOk1htC5kGEEWITSg3nxfVOAUItA7kXUIpnGTqTZD5nZkXFC" +
            "eQ1QY2ZDoU1/jqLeHOS3/g14oZzPygeHyIVwf4DM6gRioM9nSgZzEFUfCO2pnwCQLHlsiNcxZFqeybZTuK1HDrYZlR5a4u/6ACuH" +
            "tHIQhfFfAM+MBw6cdui4ezLvO1Hd55fA981sS1aOswBCNl4XAsyjQAZXALe5+6m4thX5nN4wqQRUSU6T/czdh5H/GENaMeLu2e8t" +
            "vjuAHPhepDUzkKk3BXAL4vM9wFtmdrzc/PHcnJmNoSz9K4jPNQJPo4jVGg6/M/G0s9aRedhaRNOvDYFWxqLmoGjzGOIIW1GkGCwS" +
            "JvmcsXH4R10sdAbyZ4MUfFDyW4NAVyys+P65wqMq024BvkShkvifSAPfRJlAA/Bu8XrSyAKUC0GXIqSvQb7oAmROeUTDt6OQugMR" +
            "wpTf1MXf3niV3NmImI2INJbctbguyWZkTDL7/QQM2VBguANVEz8R8v5jyJ7KrP3jsfqSSWY44xYUCpejqPNJFGIXxIP3IcB2od04" +
            "gHzM4MeZ4WeAvQT4JrABuY4dwL9SKJZ1mdmpkg/JjDMAypY1kopHlrwK2fElKDRegKLO6piwFRHAvUjDPkQpxqlMapGjhCacixHu" +
            "4svA3SH308A/IxcxF2lNyahVPM7SoAxIaSF55DP6ESjLEbu+OCZfgaLDPKTCbyMnuhc53DYze6PEHNNeDonnrkPg3I78y+vAT1CC" +
            "PYwc8oSak8aEfbEwN7L+IkL+PATaFcAXkBkuQlrWj8yvHXgXcZbdiHgeKYpwZUN0NSPAWYU4zh2IZf8U0ZUPkeYMoCrAuAnxlIQo" +
            "euXcvdbdG+J1hbt/x923u/tJd+93905373P3V9z97939Zndf5u6zE/jTJFuNu9/q7s+4e6+7/8DdV4Wc9e7eVMTNKhpVCVhip1Op" +
            "YjiE3AX8LYoY1yLSuRaZ42rgdxFR24s6mG+6+x4ifZmGnc0h5p1H7DxlAIOV+pziMS07WORTTgBb3f0NBErKvZKDX45qN+sp9Kf2" +
            "oiR1b7lMvFJRKJRQUpv52BSeNz0AFfsURPhOodDfh9KC1agasCD+bkZJ4xXIR7wCPO7uz5tZd6W+KXNd0pyU36WaUirh5IEZ1W7A" +
            "uWr71CJB61Cdp9PM2oHnAdx9HQLuC8j8FiEzW4CKb92TmLMOpU3HEM+pJxqJGZka3X1cYlg8cpMQZNxhZm5mp8ysL7oJfcAad5+d" +
            "uawXFc86kEk+j6LNi4RJVLqIzHUNqDSyE3GwFpQypTHEBKy51PgoGocjyNyG3H0GSmU2o8pBAyJx98Xfvik46hEKNaM+pI0z05fx" +
            "3J5qHzrtGlQ8QrDDkQwuQzWne1F75iAqyP3czE4Wg5OhEpWE5+4ozfSgqDqfQn446XHOAcokj3WoDnMNYt6dSGteAAbcfV4xEGGu" +
            "Jds2xSNTUUyRDMIHTYb/pFGqq5EDalP6H2WMfLlywEQjwMkhjdlEIat+FhXbDyGH2oCccyqk1VIonp0EDoxnfploNoI0qB5YnKkJ" +
            "TWqU0qBUUk2jFvGJqkbatSjoX426B5vi658B3wV2hIb0m9nBTII8B2nZXcDfoDJwS4VTdyFAm1EFoikrT7WjVNtnCPWt0r8H3P1I" +
            "NQ/NmNUsBMoXUbFqCHGiR4kzgKXuRY78FpRTLY/7VgNH3X3mBMlmG8r7EildgMy5bJ9tvFHWB7l73t3r3L0mmUlxLlbmviw4VyHN" +
            "+WIIug24z8xeLVGRzIU5XwjcgHpyVyKOtA5YF88s2b3I+KkDKNQfiWddWBkUpccZGpQpdcxHbLceOOzu75fLZUoBFZznKlSZvB7t" +
            "5FPA02a2txRLNrWCWxCgX0LtnfaQcWH8O29mfeNUE3NmNuzuqee2CrjI3WebWdUhnpj8NDAh5CzkM25GJct9wC5370QhtBvZeA8i" +
            "XsWNujwyp1tQp2QFqjo+CryW8U3Z0mjSnE8FOKvjnhcQNbgbJb6LGJ9lJ9BSX60Jae68SFiHyt45HkCIL8x1dTAuRjXcO1GH9Aiy" +
            "69R8ewfVddqAdlcX9DjaMY/FfQa4LsDZD3wfeMDMOjPA5N19LDZlA/DHSOsaUfvne4hhX4nMbQNwg7vvq4BM9qEK53XIWTchYIeq" +
            "rT/l44bBsP/bUDXuUzHJK4idzkYqvojCKaxeZO+pJdwfINUGyA3AFuBB1JjrgDN8xWgAtRodtLwj5tyG+v47zKw/AsRbKKm9Ch2A" +
            "2DXBInuQH7oNRb/5aKOqHnkrHFa4CoXVjcisHkE13EFkagtQVW4J4ibpwEIzheS0lkKS+BLwX8BToSVZk0r86CLgt5CvGkaAPoFa" +
            "S51hru1ooy5Cm7MR1cDP8kWZ9yeQiX4QcqbqZ9Vl3mRil6Ed3IBU8WlUfH8zANoTu1kXIDUhbVoaAsxHWrYKnTduRX3z14rBSQK6" +
            "+w3AN1DEGgT+AyWse5AGLIwFHXb3ZwOYy1FtKR22OGsEwaxBfOgQ0aU1dVirLu/mg+WmExg1qEC/AjnN7pgkHZEdRpyi28xaQ6B0" +
            "lmgOajZeijRwDzp9lgNy7p5Mai4icPcgzRlCtePvmtmb8cxU00kh/W2UqV+LNmBOAFBupCpnPq5tgMk1CfIoBH+AVLsBRZ41yHZ7" +
            "ApCc6RRpOuYyM6La8XCYH7r7UVSgfy4E9Ni1RqDZzA7E+3tQv8qRn3gOeMTMWjPmNwYcylCIIeLMIEpVLnP3k5SOaCNmdjLkS7/m" +
            "qXH3JmT+R6ty0vHqQgy3F/mPW1CYb0CR7BXXsbcOFLkSLfCiRaXFnB5m1uvuQ64jd9ehCLkGOeMtwE/N7N0y3Cj9ewCBuROVbq9G" +
            "wWMnmeNy6TYE/ijyO3UUakOnJuODDqIEbxSF6zbUS/oMYrDLkWo/5O4PZ2i+Fy1CH57pZ9L7MUQY7w5wXkfE8TEz2zeRbwg/tgP5" +
            "xQ1oA98JgIpbR+nvGNr4BuQvjUwKVenIJ4Yck3S5+3YUkbah0sRmxEPmAy3hMPeG0KlnPxYCDGfJWOI7COhbY3Ft6DjfFjN7P11X" +
            "TsDM4g8jE+5Cfij5onLdilFkEakqkM8GjIoBKlpMOoayH5G0XUjDbkcHOefGIp9y95eRmo9QOElWamEzEHn8dCzoZaQ5hysRNiPX" +
            "KOJdu1ECfCmF0kmpMYR860nExi9y9/eqNbEzklVTgWrIzA5kdu1HwD+g4y+9yFT+EpVMlyJn3B+vM7Qn3g6jXW5GIO5HTr2qnYxr" +
            "29Ah7yOIFy0f55ZEdN9H9ONazkytKhrj1qTNrB/od/cuFLqvQQBtRKTycqRNzyKzK5XrjFDI4VoQv8lVkC6UGkcRwLXxmhmUoBRZ" +
            "HIpr07HllWiTqirdTFhyTalI+Iufo7PIDyLTuwX4NqoxLykug8S9Y7GwNuQwV1JlEStDMk8iE2tF5n4NsDIbTTPzOjKx9ynQmaaz" +
            "Hj7BmBAgK9SC5sRHu5DZ/QuiBitQBr4hTLSU2XSHoHXILMf71c1E4wDK8mtRzpjqPaXAHkZOvQP5oqq7tpW2fQw52BFEtI65++GY" +
            "sB5FqZtdffZ37Owa8EAIORzPmR1zV/wjmAyYvah/1oaS4mtQRCxVd/aQ8Wi8qv7RTUVdDTMbNbN2M/swQmUO2fhWdOZvJ+q134MA" +
            "KDYfR4y8E+38QqIlU43DDK0bRr38Z0OGG919bda8E5imIlk7ArUFWOvuM6rR3Em1fazQiumncGBqDKn7SnfPFwkxhjqmXajes4xx" +
            "Es4KxgAy9d3xvOtR391LAH4Eac9yVPpthso3ZtJ9sSCAs1CtZg/KyNeFsMVdkFHk1I8hR7l6MnMXUYcdCKC5yBfNLr4+qg+NiIvN" +
            "Ryx+3PPdxWMqjcN6pLYdFA5y1iIKsKTo2hG0i11oQauZggaFv+lAm3MonnWaNmS0oxmVctYjoFKBr+IxFYBG0U7WhpBPImJ2KfBV" +
            "d7+kqAbUHq8h1DOfVJ8qs/hulHq8hxa/LkovKfIuQqdHbkTa9Sqqi3emayqZb9IARdK6n8KP4l6mUDL5NnCvu88JYWujEnAAaVH6" +
            "JeJk5k2gdyMS2IvKsd8Alsb3FyCO9i2UjmwB/snMfmFVnjSbUm8+nHU3hWO/qa40gpLTmyLijbn7KmR6sxCIkz64mdn9Y0iDelC4" +
            "v9zdr0T1pruQpm5DBbntUH2HdbpOmI2hAwjvIBJ5Pcq2P4/4ykh8tgr5jkOcebip4hELrA8N7kab0ol8300x30ZU4ngBlVVejo2c" +
            "dE16yiPUvi/I4v3AH6FSCci0NiNf8QbazbN+ol3hyKGTYoOmnzh9iECvQ12MlPPtRueOtoVcNUij+ibJ4Kc+vHCe51J3f8Ddx9y9" +
            "x3UEuN3df+LuX3H3C8L0JjtHY7rf3Ve4+9+5jhp7VC+3uvs3Xb9oTvflXUePJ30UZsojTe46k/yn7r7L3Ufdvc3dH3T3u12nzKZz" +
            "zhp3v97dn3X3w/H3913nCsqeIah0TOsRvAzF73b3+5FK34H8whOogTjoUzyzk0aY9ai7t8bzd6Ck+PWsPFMZ06pusVszo46Eu1+N" +
            "GpL70XnoqjoKFc5Zg1jyUgr/oUEq2HdMsu50epwLgFJ/LYc40hgCJv3PC3lUMKv6IEGZOVMvb8x0lmkGYtA9TIND/l9qboVsiYNI" +
            "qQAAAABJRU5ErkJggg==");
        D.put("qs_alarm",
            "iVBORw0KGgoAAAANSUhEUgAAAEgAAABDCAYAAAA/KkOEAAAQMElEQVR4nN2b23Pd1XXHP/scXS1LyJLB2DGODbZlwFzdkMQQAjaE" +
            "BAidZtI2TR4ybaedTmf61v+hb50+9SHT0JKGEgJJ2lzIhXAxJGAbYTA2GBtkQMbIwjaWJVmWdCStPnzX1m/rWOfo3DTpdM2c0ZH0" +
            "+/322t+9Lt+99vrB/zMxs/CH1qGkmFnOzNrNrPkPNHa+0c+tC+24WiEEM7MW4FP+GQUOhxDm6lexYj22AZ3AgI8/C1gIwep5dlM9" +
            "SsXBzWwtcCfwR8AG4FXgQ+CT9LpGS9Gzb3cdhoCDwAshhA8Xua4qqRkgt5peYLsrdifQB6zyS35pZuMhhOlax6hEzCwHXA7cAtwP" +
            "XEQLdY2ZPYcseaTW51cFUFwJM2sC1gG3AV8DdgE9flkeuVkf8D4wvRxWlOjSBtwAbHYdZhBgNwA3Ak+a2QHgRAhhslpdqrYgD8Db" +
            "gD8G7vXv3UAOmALmXNE+4NlyE0x/X0zpSq5Bc9iMLHfGx58DLgPuA7YCzwOPmtmBEMLM0rNc+PBq5bPAnwNfADYCHQgcA8aB48AL" +
            "wHPAhXhTGtDTn+WkEtCQS+0Buny864E2NLcOBF4H0Ap0mNmLIYSZSi1pSYASU25HoHwdrcwaBEweKCBgngdeQ0H6gN+3QBF3z/XA" +
            "WrTKXUAL0OyfKAFZ5DBwFgXfoWIL8N+PmNkkMEiWLK73+eWBq1B8agc6zey5EMJYJSCVTfMJOC3ATuBvgN3Aar93DhgDjgJPA08A" +
            "7wEXQwizyXNaHYz1KD5tA67176vR6uYSgIzMZT/yiR8FjqHseAI4H0KYLNKzCbn2fcCDKIF0OlA54BPgd8D3gWdDCOeWAmlJgNAK" +
            "3AD8IwrGvQ5MHphAVvND5FKnIvfx7NIGXAFcA+wA7nJgulzhFn9OqkcEJ36fTT7jwDtoMfrRYgwBE0XjrgTuQNZ+N7LWFhSjxoHD" +
            "wD87SKPlMCgJkJnlQghzZnY78LfIcq5MLpkAHnNwXg8hnHVA4zPXIUB3klnL5a58jFmGwI4/58isac5BAYEY77kAnEauNwDsBX4F" +
            "nASmgTm3pk7kZg8Cf4HcLC7GCPAM8J0QwtPlrKhkDHJwbkTZ6otkaXzOlfk18J/A/hBCITHzHuBWtIJ3oiAZ3WiGzDqmHOQx/znh" +
            "f2v2a9v804LcpM3v7XCQP43cqQ9Z6PPAvhDCKddlzMz2O6ABWdPV/n2F63fazEZDCPtK4bAoQG4J64AHgHuQW4FW8CPg58B3kanG" +
            "Vc6Z2QbEaB9wBdY4oKDVHQHOAx8jKxgFzgDnfCIFv7bVgehCFKIXpfFef+ZlDmQ3WoxrkBt1m1k/ilczru9h4Hv+zK86sCBrvg+4" +
            "YGajwDuLUYAFACWm1oUs5wFgC1o90Gr/FvgP4M2YLp2sbUWmfL8r3IRWawal4hFX/AXgALLCMQcpAhOSnzEWrXZ9elBwvwO4CS3g" +
            "Sh+nE4WAzSgIP2xmxyIxRHHru/7sr/szm9C26Mto0b4DfFzsbpdYkGec64EvIfNtQlYyDfwMudXBlEuY2bXAX/uKXOUTnEYuMwS8" +
            "grjKATLLuVAhaRv3LNqMrGEPyk53Idqxyf+30vXtdsAeNbOXHCRQBnzMgf8WsqgmxOV2AQfM7HngYgrSPEDJZK8CHkJ7m04yPtIP" +
            "/ALoj+AAwczuQMTxK74icfUnUCDc7xM7EkI4WbQYC8ijmeUjPUj/5/u5aeSGZ8zsBMpgbyCL2o1cphm50H3+vcXMfgvMhhBmzewN" +
            "ZD1bHeAu5B1bkLe8H0J4KyWj8wAl+5qbfLJrUNSfRbzjR8DLIYTIjvPAZ3w1HvSB88idhoCXgP9CpPGsB/2QjpcAswW4GWg1s0PA" +
            "u8k4C8D0e8eBfjMbQJlsxCd8NSKD63wOMa3vAwqeTA4DTyHLuRa5cbcDvd/MPko3t8Uu1od4w2aUPQyl0z0oMH+YcKM+4JvIFSM3" +
            "uog2qD8DHgeOhhAmUlBYXL4K/INP6AlE5N5KTb2IjUewzqM0fxwRyD9zvVpRUL8bz5RmdgTFuiEUR7eTkdcI6j3ASTN75hIXc9mO" +
            "9loRnAKqrfwUOJ2seC8K4vf4g3GA3vHJ/Q++ey4BSLGsQKt40ZUuWxlMwDIzm0YB/0coFHwDLfBK1+3L/vfvhRAO+j0ngB8jXtfr" +
            "43chWjIAHDSzMyEEy/kowczWIffamIBzCrnIXmDSr8sj09yNYk4zcsNBZDU/CSG8UwU4+DNW+M8OqthEhxCmQwhTIYR3gZ+glP4+" +
            "ssZmZCF3Abea2Wq3ymm0Z3zN54iPuQbFp03ISOZJWzPiLzvQCsa/vwa8FEIYCSHM+cpdh6xnq9+XQzzmKQTOQA2F81aybUcbCzet" +
            "FYlP/APgv3ELRgvd7BO+F9gWN9Bk7tlPRmBb/do+12d+pXIo+ncjc8yjVPx7YF8y4XbEqh/ya0GZ5QCKHQM1Fsda4zzJ0m9Vkkx8" +
            "CHgEuU8PcqEOFI8OmtnrZGWYfcgdd6CYFclsTE7zisz4xT0ImG6URvfHHa9fuxP56ZU+kYBW4TEUkAuVWk9CKzoQ8FGiq9Uk/swh" +
            "lFTWohgUkGd8EfGhn4cQCohF9yOr6wXeRbTkDRQPBZDzmoMoY/0G+eJp4EgyaHTDW30CBQTmXuBFZLIVFcKKrltJZkGBbP9VtSTP" +
            "nEGx82aUdHqQVWxD5LIfuSCI3T/scxoFzqQUI+VBo37BMTNrDSFMxf95CWE9Cs7rkEtOI9faF/z0oFpxa1tBBkieLK7VLA7Ux24d" +
            "r6Bsm0Ollz5gg5mdCiEUHIy3i/WKYOeK/+H/nEomAPLh7QikuNoXUA3o9aJrq5FYFo0uFSuUjTodfQOVQj5GNKQdlV22IMudn3P8" +
            "wEIvWABQym6LpAOdYMTd+QTiC/uAj2oMzFFayXhPjHU1H0dFcZ2GkQUdQhtjUFy6G21NNKjPe7E5LGXKcSVXAZ8nc68R4GVEBmcX" +
            "v7UiySEXa0IZDP/eyKPrkwikcz7GKhSbrixzzwIFS4rvn1rRHmcDmoyh2u4BFLPqkRiU82SVxTzuxnVYZSqjaLM84r83I+v5tJl1" +
            "LTVGSYCSmNKD6jstaBIFdMrwAc4nqp1IUbxKeU8s2eatzkaERKdJxKw/QdkNtCh9yN3Kxs9KskU3YpfxITMopY+iTFavtJPt/YLr" +
            "lKNBgdqL+ecRbZkgY9d9qAJRVioBaBVi2e1+fcEHGvMaSz0TiS7WTAZInqwaWZckuk0ga4/UJYc8o3Wx+1KpBKAOlL0iV5lEKzJe" +
            "jbIlJCDgIysPeKGLGsliCZlGFj9JdnqykoUMflGpBKAYNOO1EygGVXXGXUIiUWwjAyhmsbpTfSIFtKgX8b4hNKeGAGRkTQGQHdc0" +
            "ojkqIEWbk99bkNW2lbqpBolhYZqFrtwO5ZNMJQDFE9B4bVzhRrS7zbEwKDchpa8GNtWbyRKJ9S3zMVqQ5c7Xw0rdWIkZp6el+EMb" +
            "af5T/olZrA2dMlwE5szs1TrJaJTotjFTzlBBFq5kojMouKX3LBn9y0nRrvsQKuuuQQSuCfGu+8ni3UA945Ex9hj443ZpquQdLpUA" +
            "NIUyQEqyOqlzxw0ql5rZHn/eClSKiF1i8WR32Mx+isherew6lnIj34rHUg0BaBLtY6IPt6PiU6OI3LSZPY0ULqDSRBea1Fbg71C8" +
            "eyKEcKLkg8pLDumdttdcpEEudh6dx8+R8ZZe4AozOxvqbPVNGg1eIjP9Xags0YwC9jeAKTN7PIRwptLqgRf68ogxX4GsNKCF+IBs" +
            "f1ZSKgHoNCooRQvKIXa9FsWGak4vLpFYSw4hjJrZiwikC+h0dLPreCNqFh0zsx+HEMarKLHkUWxbTcZ7ZtAR1fBSN5drf4mKnzWz" +
            "t1A9JXZ5dKLjodfx46B6dt5JwX0SlW/H/PvXUCxqQW11k8BZU5/haLlxk/+1o0JfL1kMuoBq08Nx/FK6VVoPGkImOY1WpBv4HEnR" +
            "qV5JilZTqJTyCPBv6NR0GmXOW4C/B3Z6Q0MllczVqC59JZpv7GU8GkKY8nJySVkKoIjsBCo6RZO8DK3o2iXur0n8xOFtdPr5CH54" +
            "gKzgNuCvgNvLrXzyv/VoMaP1n0OWf95/r60eVCQX0EnASRQj2nzgL5jZ5gYVtubF3WMGucGT6MT2VZSW45n7t8xsUZCiVZnZJkQd" +
            "NiMLjN1xL5O0KJeTskE6GXwcreIgOp5eiTLCveiwcDA08JWDooaF42b2uOvwJ+jYqQeddxXMbAy11hTS+x2km9AReRdZ7BlAxHSs" +
            "aI6LypIW5KsZkT/EwiPd61AH7FWmFtyGi090EJ27/zs62BtB8e+zqM3l8uTaKGtRzNqG5jmLOkDeBAZD1uNUVpYEqMiKnnYFZ/3e" +
            "LtRXswtPoXUW0BYd3xfoFGpb+VfkIuP4OR6KkcTxPYDvRiepvWQb62MoVEwWza2kVNNFMec9Nr9DvORaFIu2InM/Zepgr4ajVCwO" +
            "0rCZPYsW5zV03tUPzKd80xsBn0GWtR3NMTak70XJptBwHZPAd4OZ/ZOZnTCzaf+cMLPvm9nttoxvGyY65MxsnZltSC3WzPJmtt3M" +
            "/sXMBsxsxvUbNrOHzey29DmVSDUWFNF+D1nRDuRinWSHcSdRQ8CbwMwyWFEM3nPeoJCCE19DeIishXAGWc/bqJnhzaK5LClV7cjd" +
            "LMeRef8QgWUofXYj5vttBN6Sxah6xCcZO95yiJf9JVlXPa7DKUQTDlPB7r1Yqso8CfIxYPagPsXtrkzskG0H2s3sFQf0ko7WeiR9" +
            "loOzg6zTdiNZQew46pf8NZ65qh2rptTsJv4B6guMpwMb/HmbULGrA3VR7AUGUuVqCZApKA5Mk6mz/3q0sd2Ndv6xr/sk6nr7AfBe" +
            "rVWHuszfV+8W4E9RB3ssUcwiInYc+f4zqDlpDL0qtSQ4pUD0FL4SdWjsRouxlaz5cxZVIJ4EHgVerack04jDuTZEGL+C2nC3IJBi" +
            "oXwEBcnfo+A+gDa/KfOOcSxTLOuojUX92M+8CTVl7kLW083Ct4GiZf8AeLtehl+vBUXu0YJW8Q4Ugz6PMtw0AmsSmfx7iBW/jxj5" +
            "MGK3gzFWJc9uRzFuI7LMjQictT7WBuTGIKs5hzjRU6iv+0hI3kKqdY4NyzC+0j2oh/EBRNauQKYfN4qTiPWeQ2/5DCOghsjq3rHT" +
            "NfZOf8qfsx6VLOJZPmQHCkOIXf8CvRZ1ulEUo+Ep2IHajmLD59BOei3iS7GeFN8lnb8NTXaW7Mwtulx0nVB0/TjKpscRO/4Neo+k" +
            "rgpnsSwLR3E2HdtmbkaF+J3IJfJkIEWJvUE5stp3ytEsua6AwDmE9oZ7kOuOpH2VjZJlASiKx6bVKHZch/ZwsZu/i4Xn8IEMiEgC" +
            "C2Tn6SMofh1D5YqjKCAPNuhgcVFZNoCKg6OZXYaA6UN8ZRVZrEmPZKJLxW3CKKIHpxBAg+jtwAXv5Dd6WxNlWS0ILnmNKabuFhS8" +
            "WxA4sTe6iQyg+BLfJDpd/aQI8IYx83Ky7AAVS7raPsk01qR9APN7rZC8ZLfcgPyfkOXawC6H/C/aPEbvVKdkXQAAAABJRU5ErkJg" +
            "gg==");
        D.put("qs_bt",
            "iVBORw0KGgoAAAANSUhEUgAAADwAAABUCAYAAADeW1RFAAAKHElEQVR4nOWcK3cjyRmGn83JmkTEIhKxyZjYxCY2mSFe4iVeMgFZ" +
            "kiUbnvyAsPyGkCxJyC4xWZMRiYmHSMQmNrGITSRiEw3RgAl4602V2t2tVnfLs+fMd46Obn2pt75Lfbfqrz59+sSXRL/73AN4afri" +
            "AP9+TdfdBDaSV6fiebPwApgDT20PrAngTRYHtAnsIIBdoAf0w+fd8Psyugbugcdw7UcEfI4mYtxgvAB81YLR2gX2gb3weZvI1Q4C" +
            "XAUsCOCMCHAezp0AF+F1D0zrDnZVwBZVEJC3iKuviEDNkZRucn7Loy5R/FN1mBGBToD3wJAawFcRaYvoNrCFgP4Q/psj7nhQ/m6d" +
            "vKUa4A6aVEtGN9xzM3zeC9ftht9G4TtUBL8K4C4S3TdE0e0i7o3QjN+GY2dhIE10ziB3wv320ETMwzh64f9rxPVKgFcR6T8D3wMH" +
            "4aY3SLRuw+fbwjPboR5whMCfhs8AvwI/AYMqF6nK4UMWwQ7DDWxVaxuRFWgKnAN3iKMPwOswpu/DuC6WXaQK4F3gmEWw5+HiDysP" +
            "uzndIpV5Cu/H4WUbUqpGVQDvILDw+cGaHojLVgc4QVJ4QAuAd5FVvkFgB7yMCC+jJzSWDhpfBxmzs7KTlvnSPyI9AYnSmPpgN8OA" +
            "TpABaovu0CqxgYzZj2UHlwG2iPSR6FwhY1GXumjyfgD+hvSuDeCPSPomaKy25LlUJtK74dUhcrfJutpHE3hKDBA20EQ2UZEHNL6H" +
            "cH27ubnLZBng7TDIGRKbuwaDspPQI/rYxyy6oU1A34fxPSIjuw9c5l2zCPAWAgsSlTuaWeUu8AciwEfEBZN/rxsOmik3RE9wjxzA" +
            "RTrcRwFBhygyTcjnO7joh887iNOvqR5R5dFTuMctmtxtCnz3PA7bh3XUYr+4KX3MfB8SAwLCPZp4bjOiUbXaPKM8wBvEiMUDWYeT" +
            "MUCcOCF6cb5nHfE2YxxDb+YdlAe4Q8xUWN/aolTMrpEI2oAdEb0nH7cK6Gk43ud2kJFckJYiDlvH0hxTU5rnvA/RhE6AvxA5nR63" +
            "CujU3bRaLgXcRQYLxIV1uZEbmWsPgb8Df0TGbBv57NmwL5tLS8lh60G4Rp+M71DE4dRgVclUVKWPyfVslafhfvcovraV9To9QRNv" +
            "KuO4c2FZO/R/KlqHnU9qk+Ys6mceDdCAXyPuHBIB3FFNvOcFn4H15aXr0hBx+h4F9XssJvOWBviBCif1twJ4RnRyHOuCQFu8QeK9" +
            "zAkqVcGyZcli1CRCqkpTFg2Y9dbrtDntZbLIkM6JDo599wUqci07tL8srUJPxApEOh5YbkRtJ3K9raYivcVzQ1R13fx6yf8bxOS+" +
            "RXlU4fori3R6wzLaReumMw61KgEl1z5BYd4cifd/WW600hJNLjXhcActHw4l9xEXynJKtrhlXHBW5CRc9114vQ//lzkeS6kJ4EcE" +
            "bp9oRe/Ddxe+TD000K+JgPMGfRiutRuOMVevqebTZ2tbz6S0CeAx8C9UULMldWnEZZgrotH7yGKImBrDTeQOHqMJA6nJO2IpxRNU" +
            "xt2lzlJTo3WLQE+Ab5APvoPyVodIDM/Da8IiSHPBYH3OBprMd6wh/92G4zFFtZ0xGnQ6y85mzJCu91gUuxTsafhtRBTlOmBTcW5V" +
            "pGHRgFwgLv4beUiniNtzFPr1iBXAO2SQ+kgdTpAa/IrAvmdNlY2mgLP6lOauJiiof8ViedU5rePw/RBNwhkCe039dHC6JK2Fw0U0" +
            "Ci9bcHMwzUacJgMbIluQeld1KG2IeVHApuvwsmtoCw4S7ysUEp4hsG2JceFa/1LR0hninIP6nfB72i/ySEOnIkO5S9RLAfb6vEcE" +
            "OyfWm7rIkJ3RHuDPxuFNZJh2iWBBuSeI1nqfGBe7wu/gpKqPnkZHudmVdQDeYlEX3yBrvZ057jy8u0q5g5azHVQXGiPO34fjbIgg" +
            "fwKcw0rX+RcBnIL9E4qonJfy+wRZ5Qek16fEtgq3RY147lZCQYK9Kq1LpF8h3TxF4jogVvb6iBPbCLC76wbAX5E0fBPehwjge2I7" +
            "YiMdXwfgfRTeHSNgl8gv3iEar2w2YozE7x9oohyMOCA5Ctdp7IG1CdjNpfaL+4hDP6H1FqSvpuyy4QTeHeL4GyQpe8TSzwbFPrY5" +
            "nzoca9PhHtK/ExQuzhFHzojGKRXFNFmeFdEp8AvS30Pg2/D+mljZHJCfvZwRSywT1gTYEY+DgC4C6li2LhmQK/pWBRfg8mhpAaEM" +
            "cJUSyybSL8eyjoSuiNXBurSFxPmYWEO+RNyt3X7RtEH8gNgUZm9pgIxLlXx2kcXdQiL8HQI7Q5HUz0i/W81ppZk/lx3zFvouAmtr" +
            "fIPAnhONyrJKXx65ifQt0l+DPUMGa1kax2Kdm1PPS8S7QWSOZjfrIZlSP/gG+A/wTxYtaAo21S8PKI8OkIp8R1zWfkFSM6X6spSt" +
            "ZgDNRNrZDdeEhiuen8cpW3lXDd3bOaQ60NIEfxPAD8Ri9SoOvsk9ku6M3Udg3eo4QJZ+RIvpnqZWOh1Ij9WaUZyjtp1I7cElym+N" +
            "aGEnS0pFRutD+Fy1KJ5mI1chb/nZR3pre3BBPTcy7V6oHC3ZaLnrtQroOjWlD+HaJ6jZtIus8Tn1fWYHJRMKcmNFgN3v5F0m66Ae" +
            "sbTiAZqzdT20NB7OXZaKRNoDsMi1mWsynRCjpkdkpC6o70X1iM2rHn9lwNkGry7tA/42DMzdO2c0c0XTzgUQ4Gdined4ePOEIxqH" +
            "Zk0pa0AsfiO0njdtYPWmLjs1ucmCso74GYpQDokVvSaUFbEr5Buf087S4wyLuwXu8w4qAuymkntilqIJbRHLLBB1dkDMXjYhN6B7" +
            "SbqkIHgpAjxGonZH3C1yWHBsFTJnPyCADgRuaMc2uKIxR0waUbBUlnlaN8R9BHsIdN1e5ic041fh/YzqnXVVyHnvGTHhl0vLXEt3" +
            "xTmR5l2iqw50l1hga5t2EVMcj59THNKWAh6j2XI/81vi+rYq4HVtxOyh9dyVyFti1SKXigDb0bghOh9H4eJerprkq9qgV0Swc+Im" +
            "j1KXtAiwOThFetdDZt+tgKbPAdptUt7xuo3G+J4Ky1uVeHgaLvYzmk23LdhxaNP4LCOng522PUI2Jn0+QKkbvAywTx6jqsAGmtUj" +
            "4trsPb3rBu1cl2tRO0i9XINKx/Bsr4Np1Yce9FBF4JiYlp0hQzEm7hS3H9tkY6afAeBN0X7ugO/3EO53TQXdNdV5rMUmEmuLlQtk" +
            "EJcx++OpO5n1pfO+p5lSl1YOiU6FY/URmli3N1eWribP8XDu+CAZVLauk92hklLRdoC86kEqQamvvLIaNX1wSY+4KdOVAm9eTh9a" +
            "UlQCyQJOi9nOjfuZHWNi+ri2vVgFcKEhyBxjoH2iU5+Xgy7irjOgdnLqODqF1MajaYoodxtNQmWAG3GxjNYJ+DdJX9zztL44wP8D" +
            "5qYzsG6dPKcAAAAASUVORK5CYII=");
        D.put("qs_bri",
            "iVBORw0KGgoAAAANSUhEUgAAAEYAAABICAYAAABLJIP0AAARZ0lEQVR4nO2c2ZNd1XXGf7sHqVsDGhkkEBKjGIQZHRwGExyTxBiM" +
            "7RAcV5wHl19ip1LlJFWpPKXyDyQvrthFJiepUMFDGds4YCYFM2MjMIMQIEDCErIEmltDd6u7vzx8a+nse3V7ELQQD95Vt+7pe/Y+" +
            "e+211/Cttddp+E3r2MrxJgBA0gxgLjACDJRSxo4zSXQdz8kl5cacAvw+8DvAorZ7x6X1HM/J4TADzgT+FBgDdkt6LK6PW5tWxsQi" +
            "u4GxydRBUimlSNIpwMeAyzAzPg6sL6VsyT5TnHN0sr5H06ZFlSqxPwv4I+A8Sb1t98ZrV8aYE4B5wE3ApVOZT1IXcDbweeD0Kc43" +
            "pTbdqrQc+AJwA/CgpHtLKbuS2HpHQ1ouBj4DnIt3vQs4D7hJ0sZSytpaavI6xp4M/AFwLWbqJuCt6VrIdDNmLnAOcBpwIbBc0j3A" +
            "ulLKcM0gSX3AJ7HBnYGZIqAfuB54QdIG4GDbuP549qeATwMrgY3AnOlcyHQzpgfv/EysDksx4XdLeqqU8nbV93K826cBvZgpoxhC" +
            "nI6Z82wp5ekcIOlsbI9uBK4BFsZ8s4A5U7FJR7OQ991iJ5MhXXiBAk4G/hi4BPihpB8ArwHzgc8BH8ULy8V0YwM8A7gOeFnSi3Fv" +
            "ZTzrRmBF9OmNuXqBvhg/Mh1rmk6J6ceq1IeJLvHpxov6CrYl3wdOwjt+Eq2S0kWjUicCnwBexYu9DUvYongm1RyFRh2ZDsl534yp" +
            "iEiJqZmSrQsv6EbgI9H31Ji/Zkq7R1kF/E3cW46NbPYRlq6cu7vD+PfcJmXMeO6vw46kOM/s0D0loR+75HpsMrGrrb+i70Xx21h8" +
            "Ut1UPSc3ZAYw9B7pb2mTMmaiB8SkJfDEjCBwJkfuXL2buahC6+JGaZijtr7d1e8j1bViTM7bFbQcfsZ7VakJGRMu9TxgAd6JHcCu" +
            "uB4upRxMAoJJs2iY0L7gUl23q81oLDjvd1f9RqJvD43UJGPzGT3YXc9IxB30JMP6sBrOB2YDu4G1pZRDR8WYym7MxhD9k0HkRuAd" +
            "YADHNAMxyQFsM85sIzp3dYzGlY8BW4FtwD5gEDiImT2CVXIWjTFfgr1b2pFkZM2YLmzYr5f0TjBidoyfH0xZiOEDwM+ADcCe8Qz1" +
            "ZKo0GMxYhnV9LIgaAvZXizoQ/U+qGDIW173AMJa0nfG8XwBPB4MOxfhhGtfbj1VzGXAVdvcrgkGz414yPRl0FXBBMCk9Yz9mcl/F" +
            "wGeBN4PucVWtI2MCl5RSyn5JT2HscU4Q1UtjaFMS8uFpRBVM68LStSkIuh94In5LRkCrMa7bS8D/xbyXAbcGA5ZWzOnB6rKCRs0y" +
            "gO2uPknTm8DTicSPijFtbV8s5iNYXNPg9cb9NHZJTInrLqx2dwM/BtYDO0opA/ngylB2asJ2bAiL/C5gLWbQTTiUOJHG/vTQ2KlU" +
            "26QnJWszsAZL+4RtXMZUnBwGngR+D0eyaTjbvUoupmAx/TnwPeABYGMpZfRwJ2k+VotlOCJfQoNi92OGbga2SdpSStkehn6DpHeB" +
            "V+L5XwB+iwbxpgdLw13TNRLjnop5JvRYU3HXIxHMvY1FsVOwVhvBAeAe4E5gdSllbzCjG3u4K+N7Cc7cnYoNY3qdIWzQ3wF+DWyU" +
            "9ArenI2llH3AWklbsN3ahaP5fhqc06ntx2q0oZQyadgwmbtOHdwFrMOeZA6tGAIaqdkBPAR8C3iylDIcz1kK/DZOE1wdTJk7wSJO" +
            "i+9DwF5spB8D7pX0C2BrpDN+ErQNY8+5gFb3nq3gjV0HbJ9ozUfdJF0p6cdyG5U0LGksrsck7ZF0p6SrqyRVj6SLJf2dpDWShiSN" +
            "SDpYjR9T08bi90PRb6z6HJT0iKS/knS+pJ6Yo1/StZK+J2l3jB9qe6YkfUfSRROv8ugYktmymZL+XtKuavLRWMCgpIckfaYiuE/S" +
            "xyT9u6RtMWY0Fj1aMbRuY/GsA9Evf8v+eyW9KembsVEzY64TJH1O0oMxPsfmHEOS/lZSyc9k6540tRmuuyf6bgO20Fj+gsX9ZeB/" +
            "gAfCJnUDFwN/CdyMRTzVpjaO7QRmlJzpi3S/2WZhV30r8BfApZJmhB27B/gRhgbpGYnxG4L2mUB3u9HtxKie+maHAd3YJV6B3eR1" +
            "QVgySli/VwM/K6Vktu1c4M+x3s+nNeM/2W7V0XOne73AYnzcMgz8o6RXSilDku6jydvMwUwG27Nbse16WrZTezJ8SNyW1y2MqfKq" +
            "szGCXR4MuSKulwaT+mjc3wjGJ4/S5FtPAf6ExhgmppmullBhMfC7GEl/C3gXeAN4BHu+VdF/LPpeHb/dGDQ/L+l54FfA5lLKAaqW" +
            "9mAGht3nAWdgzq7AO7+M1qAwrzNTdz/wXCDJudh1fh4zUW3jpqPV+ZslOBP4iqSfllL2SnoGb9TFbf37sRSdjrHPNZiRm4DXJb2J" +
            "mfxKKWV3Sswc4BaMKE/HQVftijuJ9SiWktUYjIEZeQsGbXUSKoFfPg+mrlLj/Z5qtRIfv7yOw44NOEj8Q4yRwJKdqp/zL8ObP4Qh" +
            "wa+wnXob2N1VDdwbk82i1XiVtk8+eDfemY2llDHZRa/CqtdFq11pR6GT5Uja56w/dZQNtiOXASslzQqT8Fow5wCtoUE6jExtjMb3" +
            "3KBpK4FzugDCqv8n8F84HhmagPhMdO8DXoxvsB6fjVOYuTtJ0LFs3dgmXoARNHjXH8eb3SlEqOkaAX4J/CtwZ4QeTedSyta4+U/A" +
            "c9gNtzNnlCZZNIAh9q64dxY+72m3Rx9ES2k9Mf4exLbjAE2Y0E5LOpA1wDeAO0opuZYj3PVu+YCsGzPmKjqnJUexyG2N6BecpLqQ" +
            "Jur+oJiS9msV9p7PBZ378aYdCppK25hDOC/0beC+SLGUju46fPlW4AdYDLvwwVnanRTJwZh4TzXZqdhwdwJux7p1Y2N6GhwOfHdi" +
            "NU+4UCe2hrEN+mcc6O5qx3EtQWSVoNoekgPwJZz7mE1rdm44PpkbXoAxzigfbKsN8iJJ/WEnDgZ9pa3vfhyQ3g7cW0oZ7ARuj4iu" +
            "K+bslfSjePhSnKhqz95n64/P8W79eHMySZ+OApqE+wuYKT8FhjoxBSZHpKNYpXZgBqWFz5Cgtj21ez9eLdF40lHTknZnK0bJE9bT" +
            "TJTz7cMw+usYm2RCucYTOfEAZiC0grkPug3QpC0z/Zrq343jtmsIiZL08/GOUI5gTBjgXsyUv8YJplk0xms0PjNjMgLgDdKcDByP" +
            "JmCoquRKtUp7CF7vYpym7QK+GQHlULv0HFalKvSejTNtX8Mxxdxq4kSMMzCz5lXP2oaB1fGonRvDadAd1W99NEnxRLy5cYtwTPd1" +
            "HIj2QWv6od1d9+Fw/su4DiVjpnZY3oUZc5KkmYFl3sRIeDGdz6+PVRNG6r/E8U4GxQtpSkPq09F03wtxBqAXHzPfWyfsa4B3AmbK" +
            "VzCwy4dCgwPyeKJgfT01+g3h4G0d1uGsePig2iA+g9oUf8/B6Y+5tB7nQHNoSNz/BGEiJK1OwHo4b4rLtr6KAV1m0GrxS9sizOX5" +
            "+HRyNQZ623DwNkAr5kndPRY1xSkBe/DRyLvx+wIML+ZX9ItWqcnWj9VpJtAXkjOYxPbhCHU+RouHODK+qI9cMwq/ClgWWOAAxggv" +
            "0HrC2H4I3+5G3wszakM5CDwPvFgd5p0di51Ha4ozz57yNLWbJu2wBDucedCo0gBwB46sM2u3giYU6KU1ZBfm9FnYFr2Kd+s14H9x" +
            "0utEGhGezsqt+vhVOAd9F87KoaZuOHNCmYupMdcg3rwDQftjNGfa+0mCI7ZYh5M9D+BTwvOxvVgZf+dZUGIC4u9PA+sk3VdK2Snp" +
            "IczYz2KVOhYtJWY7RrCP0eCoy3BhY6p/VlikbdmOmfgqjpfWYdC3B4O+EWj1SkNYrPYBb0vaiLm4CAeHZ+CduILmzLgLS8cVwBpJ" +
            "23B68Ds40r2SJkc8XcY45z2IUwbfBTaFV83c7kU06HwUS9VTWM03B43bgLdKKYOdJmkR8dqPl1J24rKNTJAvCGLOocl7dGOXfiMu" +
            "xPk+MCjpCRyPdGNmzqCB6u9VrdrtyhM4ZbCmqiH+FPasc2mqMEYwvnkQFxfsqxPfcmGBYs3jR9ctlEQpWeQqwJZ7Ho2Y5vjLgNvk" +
            "8+Q1EYD+JPoPYvScr92McHSFhHXtXaFJqf43ztEOSZqFS2NvxTmhROhp4/J0Y08cs9QC0BGQTrh7wajM05yJRTRLUOtPwWdOA8GI" +
            "Z7Fhuwsfzm+J+ycHgXWWb6ptENuCR7CjeLiUcihA6SU0oDTXlLT14FzNpbhSff1U6vKmeqjfje3IuXGrtvKpIosxWHpH0kH8BsmA" +
            "pPsxxrgZhxoXYrWcw+TYJpP0u7HBfBB7vfXhMHqwat+GUeyJ1dhkSsKFCzAD108yJzA1iSEWcjl24SPVuGEaXFBwBu1L0f92SWtD" +
            "/98C/gN4GO/q1TQF0O2Req0G23D5x+PY0G4EdpdSRquzsK9h27I4yY6xSWdiqXOi//2S9rzvctbYleXBlHTXh2gCyhq89WC3fjPe" +
            "vX+R9AhwMKTnJewV7otnnhXfSzAuytOHLTju2YCDwz0VQ7rkN0+uwUex19Ic7qVNSVryWjg2yrqcOiV7dIyp1KgXc/qM6H+IxmXW" +
            "ULtGuKfg6HVREH6PpCfD0O0CdknahOObEzCQTO+QwGtPFh1VNPVjibshnrsqxteotg4a63xvwRtxOT6BfN81eDMwvF5OA/Uzk1eD" +
            "PWgM3hi2Iddiu7QKeDRyH+uxId0XxxW7GKeFK+3DknphMCUZsphGKmi7htYYCcy4FRj83UWTo+nYJnSZcbp4NfBv2CtlG8UGcRfG" +
            "Nntj4mWYgXU1Z0bj7+LjimdxFLwnxu/AEpL9ujFiXoglbhFNOe1HadQmA9pEtm9hr5XjZ8UzFtDKsOdxsPzMeNk7mFoB9Cqs5wdp" +
            "CpZ3BiG/xhB7E97Z24IxvdXj8ih0ITaS1wWT9sdztsb3ME1p6iJsC+bTFC9nDXBt31J9hnHZ690YvS/Fhj2PdPppmL0bM3kdLuLu" +
            "qE6TqVJWOn4DY5Qtsah9QUydkjgFi3ndUpTzgH8kCGxPa6htTNqw9E7JkMzIpaTnMw4Fbc9hT1Yfy6a0LKKJ+7bHmHHbuMnwuNyP" +
            "UebhisrxkKKkfZh5Gbgd8VhaczRdbf06qXXtujulLGosdQAYjCCwU1XmgKTNNADzQNtaW9pUcEwGl6b0yLKsEszKCochjjwSTaOd" +
            "8/VyJLhrlxpozfSnLYEjDf4wzTn1RK/ijNAUIUzYphTQ1WVYHeKpvMz3Cw7QZPDqlgvbjRk1j8lzw8PYsPdG//HKX4exDZy0sLm9" +
            "pGy8NiXGTCW2oHnZIne1PkrpxgvMaqd+DAJX0tiguqU6voFdqzBkuAi77pyjzkXn2yvTsZb3n1mrJhqjAX9pG3owwW/gNMG92Hss" +
            "xhKwmPhfDtHqvM1OHDB+G7v2l3E89HEMHdLu1e86HW1gOm6btpRjJIoGsTsHE/ku9hQ/xAHg5gj7h3Dt3rnYfdeJ6lzcMzg7tw1L" +
            "413xrPW4nO0CDCJHqs+HizGSusIAH8ASkimCB3CKYA3h0ULHR3BweCEO7k6tHpcveq0GHq+SSsOSXscVms9jzHQdxiYHY84J0ezR" +
            "tOlMUoOZkqDtDiwlW6riolS9kUiDPoyZ81la7cVjwKORQz4MwIL5A5IexpLzLPBFzJCBZPxU7cgxb2npJV0i6c/k9wkWtt9vG9Mr" +
            "aY6kL0t6TS51H5K0VtIX416ncaW6XiTpeklflXT+eHMd9yZpvvymSf49LpERICJplaTbJW2X3zn4B0krpjC+ZtCyOEn9cDdN8UWG" +
            "6Ncv6RZJj0p6WNINIU1THX9MJGS6bcxR//uA4vcPnsNvwwG8FLncSRdclflPu135UOhjpDdOICq4xovHftM+BO3/ARlW0sCSQGGJ" +
            "AAAAAElFTkSuQmCC");
        D.put("qs_pin",
            "iVBORw0KGgoAAAANSUhEUgAAADAAAABICAYAAACwc3YrAAAJA0lEQVR4nOWb2Y9cRxXGf6d7ZsyQxeAN4jgJjiGQCDskEFBAKAoi" +
            "QuIBsQiBkHjhL0NIvIKEgIBDEschEC/YseM9HmeGcTK2Z7PHGXu27uLhq9O3+s7t7tvbvPBJrZnuvrfqrN85VXUbBoQQgg3z+lYY" +
            "yCCOEMIoMAZUC+YJwAZQA9bNrD6IOXtSILWemYUQQgXYBTwJfBnYBzwMfAoJvAp8AswAl4FJ4IaZ1dIxzSwMVYF0khBCFXgUeAr4" +
            "GvBVYDewE/gMMA6MAnVk+TVgCZgFFqIi/wHOA9fNbKMXRUor4AOHEEaAA8DzwNPAoajAE+nlJcafAU7H1zngAjBpZktlZeo0wSaE" +
            "EB5Elv4Z8ANgP7AtGauSGzP9v06mWEChFVB4fQj8G3g1/l1Mw6sdRkoKPgY8ArwE/AR4EXgQxbiHSAUlbyuj5BXzRB8FvoI8+EXg" +
            "MeC1EMKkma13kq2jByKzHAB+DbyMYn4HmRXriUB59mkaiswDIIUh80wVJfpV4HXg98D7nhut0NYDkV2eQVb/MQqZ0Sh4hSxkUou2" +
            "QifPBOTVg4jBxoHfhhBOtkvqTiH0NIr3XyJqHM1NXCm6qQ2KlDA2h9ejyGjLwHoI4XyrcNqkQMI2u5HVf4XiMrV2ftJBwkPtYeCn" +
            "KMkXQwjTRcVvkwWj8KMo3r8XhR+hmRqHJbzLNIYIYh/wHeC7KLw2tSCbFIjCH0QufB6FjYeLeyGg5CP+TS0Tcq/8Z53gc1WR4Q4B" +
            "PwIOhRDG8/nQUCDR7PPxhm+iilpPrvVrUivkBXMP1YH7wF1gPfkuoKrcll2S63cA3wJ+CHwuJ2uWA0nofAn4PrAnTlSJk+bDpiiB" +
            "66hNuAHcQrS4hty/B9iLeqb1OHcnEvE59qBwPhJCmDGz1SYFkv5jJ2rGHkc0VicLnVaoknWac8BrwN9Qr+Pd5ziZYV5Bni3LYAFV" +
            "+8eBZ1Hv9JHLnLfAXrIqm8Z9mUluAH9CBeg8osAamaUngCngJmKXJyn2bBEMsdJzwDvAR/5FXoEvoNgfj+/TatmOfeaAN4DfAady" +
            "nL0eX/dDCMejYtuRkfbSufHzHNsGfAO1HP/yL9Mk3kEWPmM0J2MrBvGGbAL4M/Cema3HCt4sRQiVqNhl1Cpcife7kHk2SxXwyv8E" +
            "sD9lo3Si/citozTTJRRb3wdeBa4BZ4jMUlRwks/qqHWeAFaSOdJutQiVKNt+4IAzUarAQyjOWg3SysUraKGyTLEFG4iTrqFFzR0U" +
            "Wk4UVYrzLd+y7AX2NXkgvtmBWChfiDr1PO7+jkUqKUJpA5cKWmQkJ5Ia8vAjwGMepqlg2xG9pYKVwSjwabKKXQZeZZ1E6mSteSt4" +
            "yI4iktmkwAPx1c3C2lDPshtZpgqltkx2IW9vIyuU7VqNlg1kJfd//oJ21GnIglXEDt+m2YNNSJT6bLz2KZq91mlB5Em8jYT+UwUW" +
            "UTKWbbpciQ3EXj8HXg4h7Ep2LvLbL7tQl/sLtFBy6iwyXgpftnp/1SCMtJDNoGr6bCJcmZiuoJh8BvgNsDOEcBSYMLOVqMgDiD1e" +
            "QuuL51Do+f2d5nFZvOLPOC2nCtwEPqb7VVYVWWMceAGRwUHgSghhMV6zHYXZC6iz9P6p3SZAXoEq8sJ1YLqpF4qudgW86nUDF2IM" +
            "+Drq4ZeAe4jrx6ISDyUKl7F8HvWowH/9gxFoxOds/GIe9d3uMv9blBeuqCe0YwwxTf5aD4VuDeTCLwBTZjbfVImTdvoacBa1B2kv" +
            "tNFCgZS3XQlnppHcqxeLk8y7DryPWpAG8paYQjtjd3JCttslyysxrDXzCnAKhVADaSsBCp+LiFLXaN606kRzw0QNuA1cQq07Rd0o" +
            "KOlmUEJ7p9ipwDiGqcR9lJ/TiBwaaFrUR26dIltRVZH2pTZah4glFBkfR8JpREMRG8yjBcet+L7rQ4cBwlnwDlpK3shf0FDAY8rM" +
            "7qFkuYRCquy6eFhYjrKcIIZPujfUJFjimnnERtcY/k5cK7j1J4G30ClOve3OXKLZKvAuOjnxJmqrkN/RuwT8E5FKk/WhdWjUkOZX" +
            "iLS1RfBVlxttEfgAuGpmG0XrjEIFopZzSPurbJ0HvGjWUB26Alw0szuJXE1omZzxjOo4ct8aW5MHThhO3+8CJ9qt8FoqEOvCBPAm" +
            "8sJWKFFFjSCIMt8xs4vtbihDj1fQptUC7TvTQcDH/QTVosudbmgXQj7YDeAoOgpdIUu0YXgjoK5zBnibXONWhLYeiGG0SkwmVEi8" +
            "NR5GYhs6TziPrL+pcOVRtsLeJtsV9iQbNLxw3UThM2lmtU5bNG0VSDS/i5L5NLJKmWPVXrCC9k0Pm9lcmecmSnkgdqnT6PDiNK1X" +
            "aL0gHecc8He0Ni+Fbpq0OmryTqLu0I95+klmJ4Qasv5J1PesQ/vYd5RWIA42hdbMH1L+dKXj0EiJCaTARDcPQ5VWIGGkU+iEpEb/" +
            "Cninu4Ko+mSZxE3RlQfiwBdQnF5nMCu1tTjWG3HsUqHj6HqhEt17AfgjWrWlJzndwDe37iKDXIjHU115tSsFEsvcQlx9Cbk/3bss" +
            "C9+ovYbYrWPVLULXHoi5cB954RiiPBe+0yFFY5g491wc47yZ3e3lwb9eQsgnWERxe5FsAdLt4Yg/3LTQrRyOfhbrq6jhOoYavhqt" +
            "D+qKMBvvfQuFUlfJ6+hZATMLcf//CFKkG1oNiIpfN7Olfh6C7Wu7JDLGGdTozSJKbHUg7mG2hjx2DDhXdCjeDfre74nPeXpxc0Zq" +
            "hxXksePArX4fQe5LgSRmL6G6cJ3i54C8Ba8j1voLeiyh7zVF3x6I1HcbhcSrZJ1kGkpesedRy3AGPQfXdy81yC3DWeAwWr2t5r6r" +
            "oA5zAlXd6V4YpwiDyIGQFLf30APdM2w++LiJ9jfPohVeT7SZx0A8kCtuR5AifkCygRL3LCpam7bI+8Ggd51riGHeRGuHDbKHu48C" +
            "b5vZvV5/K1CEgW+bm9ky8A/gr2jldg89Q3eYErsM3aLU0+tlkYTGVVTcXkTxfwL4YFA/O0kxUAUcZrYWQjgD/CF+dNbMlgcZOo25" +
            "BjlYivhLjz0oLxbK/Bbg/xL/A1GchLta2GykAAAAAElFTkSuQmCC");
        D.put("qs_gear",
            "iVBORw0KGgoAAAANSUhEUgAAAEMAAABICAYAAACtDUiwAAANJ0lEQVR4nO2c2ZNcdRXHP2fWhAlZkCQQEhLAQEKCoCzighS4oEWx" +
            "WGohVZbFi/qH+OiDTz5YPliFS7mAWlIIUkDYDEJBCJCwBhKSkD1km5lMZunjw/d35v76zp3unpnu8YVTdat7uu/9/c7v7FsPfAqT" +
            "YPO5mbv3AwPAeUBXdgUetfS3AxPAEPCJmdXmA795I4a7DwDXAtcDlwOLgJ7sCgI4MAwMAm8DTwL75oMgPZ3ewN3NzBxYBtwD3AGs" +
            "QIzoArqz2+PA4+n1DeA0cBwYzNbqCHScGGbm7t6FiLEJ2AD0Uxyc7L2VrlFgI/BUp/GEeSBGgj5gFbA87VlDh/X0fjpuLwWuBhZ2" +
            "HkWJacfA3cMmLQbWI2J0U3A+1CSu3KgCLEGStKKTeAZ0lBgZBDGCw7kkBFHKniXgfGCTuy9LKtcxo99pYgTiy4AvI6KMT397JQwA" +
            "nwc+00a8KqHTxHB3XwB8FrgUWEChIs0g7hkArgMuKn3edmiJGLMRzcwNXgbchDxI2IiZrLcAWAdc7u79ZlbrlKq0RIxZ+vYud78A" +
            "EeJm6u1FqwGUI8ItRm55o7v3dSrWaOhaEweCmxNArWzEqhBz9x5gLXAbCrTWZ3uNpde+FvCLqPQ84Bso7uh19zeA0emIUsYv/m5G" +
            "xEpxCxF39z7gbuQSPwJ2AXvMbLQBApcAtwLfRDHCGuBCRNAaRf7RXV6jChUKKRoBjgJvAk8ATwPvm9lYCuomn8kP7e4LkYvuB4bN" +
            "7Oh0m00hRkaIRelAP0UB08eIILuBw8AhYA+wF3HsQuAG4HakFlcj4xfS5enK44jZwCjwLvAisAXYamZ7c/wTvmuy1+VIuj4C/mhm" +
            "R6tC+0o1cfdupKM/Tgdbkv6eAE4BR4B9wFvATuAssva3A7cgHZ9Iy7WTEA70AptRsnc1cKm7P48kZwB5rfXAFelamfBZALwH7Hf3" +
            "x81sqEyQOsnIpGItcB+SitUU0SFI58ezZ8eQKPeUrvjOqY8u2wFhS8YRYz5Oe12MJLQ33dOf7p9In51BKvYL4DVKKlUnGYkQ3Uga" +
            "foDErC9tGgv2peciXlhIvS3oyt7nYXc7CVFDBO5BNirC9V4KWxT5T37O81Hw93XgiJnty6WjSk2uR2n2RgqLnxu73AaEcct1L9xh" +
            "qEVATjCjqF3k6pOvGUa2lu7ND5mH7d3U4xe4WPaav1+RzveBux8ys/Bu9dxy96VI5yMu8Oy+/N5yqj1dMGXZc/khDTEil7RYo5d6" +
            "Ducwka5GQVcZt6rvrwFuRPZmcp9JyUixwTUoSFqdPp5ptNgIQRBxTwEn0+tpVNUKu9KLrP5ilL6fj5gSlbC5Blvx/GLk+V5DjmDC" +
            "3a0n05klwLdQUpTXD0L3ZksUR+7wk3S9C7yCqlgfIDcdor8Q2anNCY/PIY8QccJswvkcLD3vSDI+Al5w9wMAPVlwtQn4KvLLwYWJ" +
            "bIHZgAPnkPv9B7ADxSlHgVNmdrZ0/0l3P4KItAV5h5uQjn8BZa5zkdRQ2xoi/LXAXcDvzWww1OQC4IsohO6nIEJu4KrsQRViYSgN" +
            "qcMW4CHgJeDj3GDB1CTQzCbScyeBve6+F8UH9yAvsJZCrWZDmNyOrUHe5UlgVx4PDCI/PJZtFCI1080c+f8ngd8BzwYRynnDNLlN" +
            "fs9B4KC7H09rfhtxNHALw9yq9Ia360a2Y3V6lQE1s+Pu/gSqO/RTSEhQsVUOBHInESF+g8LlWtimVjLOUm4Rz73i7scQ0y5MOHZR" +
            "H+m2gmfYJ5ANex84AfXucg/wa+BvyMKOomBrJpJRQ17iZeC3wCs5IWawziTEc0la9gL/Bh5FdiePKVpdPwh4DHgc+BWwf8pd7t7l" +
            "7hvc/efu/qG7D7r7hLcGtfT6qrvf5+7LskPMGWIdd+939y+5+1/c/Zi7j7r7yAzwHHf3A+7+S3e/Lo8zyiGym9k7wMPAI0jcWy3E" +
            "GKLwFmArcGouEjFl8VSXMLNzyC3/E0lzo5qMI+nOm1OHgD+j7HV7MthARb6QOLAT+CvwH2RUY+EcIlGK72rAdiTGh82s1u6KVFao" +
            "GQZeQO3HszQ2np5dR4HHgD8A28pSW0eMQD5Z/u3IE7yOYoUyBDFioyHgHeCNquJPOyHheRDFLQdonARGqH8KufcHgR1mNlFm1pRF" +
            "MnEcRNR/BAVBZSOVx/9B9b3IMHUMsgOMIcnYTT33AyJoDBV5C8U7O81spMqWNUurh1Ct4EzFd5FLxMZHgINmNtEuo9kIUlf+TeQa" +
            "p2tRRnY8ku57DkW5lbasGTG60ehAuXibZ45R69iH8oz5hP1pz2bn6EUEOdRotKHZIoayx4XZ32Es83pEDenucZh1a2FGkLg7hjLf" +
            "XB3qbqMoAvXRJChrhRhRbyh/HhCWfBBZ9vmG4Xbt28pIQs7lvEBT/u7/BVZ6nTU0k4wIWkaR9R7LPg+IDDUKMfMN56Wr48SooSRm" +
            "kPoiT3nIpAvVFpdC+0LwRpDVYZYgA1neM+zZOGLiSLM1W6lYL0I9hy6qy29BoCjTzwuknGId9d35nCB5qTHsSsNxiEpiZFTfgArE" +
            "6yjaA7nVzgvClwCX5IlPh6EL1WzXZ7jkEEXn6NksAlZ6fStyyoJ14O7m7r1po5+hCtj5FAcPV5UbUUPE2IxGEDpGkEwFe4CrEKMC" +
            "hzIEvgPoHPcDy6ebACq3CrpSjHAF8D1UVVpOYSPCgEYmmIfoC1Fx6Kryuu2ErNG1Grgy4dcos46iz5XAvcAt7r64iiDlRK3m7lcC" +
            "3wW+g7id24kqnczfb0BF5aVJwtpqSLP1+lGnPyYBWyF+FIB/BNxWRZC8b2KIsz9ExIiZirxxnHex4n1Q3lHT93bUCnjazE60q6aR" +
            "F3eQCt9FYS+mU5OA+G4xaoeMASPu/qyZTXqZnrRJN5KCB4A7kZpMR/EqTxLvHanJA8Bxd3/RzM61s8iDCHA/aoMupr5t2Qr0ocGX" +
            "YWDU3f8bLYvom6wBfoIGUy6naNjk1WdoHthMICLemBAedPdXoW7Ga0YQEpHwvAxJxB0ophmn9cEXKBg5gJi+COhz92fM7FyoSR8y" +
            "SBcxdfaqHII3gujJXoB6HAb8HXg+1UemtAoqMa64x92vA76PiLGO+lQiuvLNIK/BxLzZftSX2R0LnkB1xVtR12q2Ih0EM9SguRsN" +
            "i1zk7luA/WY2GfhUGdi8nZCM8FpkmO9FErGWevVsNG7dCM+YKVlCImQQ4xgq5N6JIsmc6rNJhAyFyCuQfq5K1zZ334/6FSfNbKj8" +
            "YDKQS1G+sQp1vG5DXAxG5bjM1I1H4NiNClJbSXWYaDzX3H0XIsgaZECjFhDV42YWu2pTkApuRGp4GInkq8B2d99NUSaMcsE61HDe" +
            "TBFhxg92aqW1ZwMhVWdRnfdR0s83ejK9HUaV7U3Is/RnD88FutFBFiKOX4qiwdOonDhEMYzShzzE4nT/ADJyZZWY61TABGLIU6j6" +
            "5e5uPVBUjdx9B7ANua3LqO9aN9tgOuTyz3vQHPmyFhHPpTJvH86FEGOICS+jicEayFZFrzVaBEPu/hySjosRd/K0fTrIOVeFfNwT" +
            "7jruLY8kldfL74+/o+RYN/tJkR5UrZd7xCgkbwV2Tzvglqz7y8hubEbhbjSgw/CU57Ri+i/uC25WiXL+bHkWazqokoJysznPpstB" +
            "YT4iEfWZx4AXzWw8j3+mlP1Sqf8l1L5bSVEvgCJBCyKcQoXgUyhYC0+UtxDaXeiJuso4RWU+RjHLE4Y9FITrRe3SZ4AnzOxAORCs" +
            "Gn001K16DkWSX0FcP40aRe+hUaRdqGl0Ah36BpTc3YhcYCBi5X3mADnXhxIe/0J2bnW6YiB2JTK+YYDPod7JQ8CHVYtXImlmo65h" +
            "9QfThjXUTDqM+iMHUcNoMk5w9z1oRuptVBC6HlXIJmgfxKF2om7fSyi63eca716OCLISxTgRJqxHpctH0LzI6ar0oKEIu3sEPiNo" +
            "iHS09P1kgSdJVQ9Sl5tRLWRDQi5msWajMuEKzzJ1kH5X0vuucnMo4bYKRayb0/MvoB8CVHrHpsiVEqVp84pSaN2NuHIrGrv+GtLZ" +
            "KB3OBGrIFb6O4oKngNfMbLjB/uUhl+70WcMaaFNdrhs0b5B1lr4bB3a7xo6WoLR+OUUZbiYEqaFO3cPIqB/KaxDNcEuft/S7uI6V" +
            "55JOnkHd73coYo+ZwhiyV9vMbM90HfR2QMeIAZMiug8ZvDFmJhHB6UE0yXswZbFdjSR0LtBRYiSk96HywCDFAWdymJPA86QcYobP" +
            "zgg6XcXuSu73LTRLFdLRqoTEVN4Oq/ixTLuho5JBwcVPEEGGS583es6QVLyLpKrj0GliBAyj+OAM9SNHza4jKLqcF2LM139JGEFh" +
            "/GlaU5P4fgjNk1UN2LUdOkqMTL8HUaj+Oir0LGJq5zzv6Mf4w/sUvz/r+ETQvEhGyoT3An9CErIGESWHCJH7EPEOo3LCB80ix7bh" +
            "OR+bBKRkahlT/7MKFAWa+F3aOeB0VdH4U5gH+B/KWsEiaaFwPwAAAABJRU5ErkJggg==");
        D.put("qs_list",
            "iVBORw0KGgoAAAANSUhEUgAAAEAAAAA2CAYAAAB3Ep8CAAAKZ0lEQVR4nN1aX2xkVRn/fd+9M53dTrcptiKsQgubxrgrwXQkuyWY" +
            "2Zho3LAxMbDhSROfTEyIIfrkw8zoC7pmNQooIRoTH4zsxiAkKkZsCbBh0ztlxW5iMIRCAIG2SDtlpjNz7/f5cM+5vXNnOrOztKXs" +
            "L7mZuXPP3HO+/3/OATpAValQKHD8s9O4qwEthKkqEZFuNTj53N57njdKRF8TkRuZOaWqG6oq/a6Fmfv8yyZEBAC2XDsRMYCUqjaI" +
            "iAD8K5fLnXXtgDhxnueNMvOtIjLOzG/W6/XysWPH3iYiTTCBzKSHVPXbAG5SVTaDnD5pUNUt198TZm3dXkDhMIKqMoBzCwsLf3LN" +
            "zERE+uKLL440Go1vqepXRORGAFkRWU+lUq/Pzc09x8w/I6I3O2hKCkBGVdNmMUxEbtsSukBErpz6cE7tpr0AYPjDzEwi4g4ODrJr" +
            "iZmZmck0Go3vq+pdAD7pOI6jqmDma3zfv56IbhWR6+bn5+8joqWEX1AAwswCgFRVrkCc/ZpMG33oYgIAwMwQEYgIW2ZFUhoaGvqm" +
            "iHyDmYcAqO/7CkSSCQCkmfkeEVlcWFj4IYDm2bNn40wgwxQyNtaX49wNRysiEjMVXVxcBBORXrx4cVBV70mlUqOq6qpqZL9Wbczl" +
            "AvhqvV7/LBHp2NgYmTFtnDdaIFeoDX1DVcn4n62upNlGhMHY+yeM86oTkY+YSlkCRCQAcI2IXAsAQ0NDlyu1HWfA5UBDCBFFQrEM" +
            "GDJhggAEZmAyRKrxoPtVtS8Ht1dgNKBFIRkAfN9/Q1U3DIEDAJy4uhib9s2tbzTkqgADQK1We4uI/hNLJgKYuAmgCQAmrjtEdLHR" +
            "aFwCgEqlouZZm7Zg04TUOp44doe83rBh0Pc874yIfI6IDiI0AxiNSKmqD0CIaFlEfn377be/akwkCl0m8xNsJhxJIpP3eyK9Zpvd" +
            "5XK5Z5j5BwAWAThASAUzs5HwiqqeXl5efqxDyPKJKCAiax6CmAZ0ujpoxK5dsfnCPCCW4v6mXC6/KiLHANwmIjcR0TsA5ono2Uql" +
            "8tcTJ07UbUixjFBVh4j2q+qgcaaKhISTeX7yXkT6TZ37grE6RSh0B4b2yJtblZ2amnoKwFPnz58/ODAwcB2A915++eVXTp06FWwO" +
            "DccWi0VLzNuq+gyARVV1DXOC+LtFxNYNICIEQZCsfGSnXYPRUiBM3efz+XyjzQ5NodCWV6uqTR87JT30/PPPfzyTyexzHIcbjQbF" +
            "c3v7fWBgIPqtXq+3zB1/ttNoNpvMzCu5XG51y0E2syoUCjY/uCqxbYSpKidqgz2Nu+++W3pVj7tSoHzY2JLADsVDVywsLGTX1tbS" +
            "AJDNZpWZW979/vvvR/eDg4O6traGbDYbvX99fZ0OHDjQ3+r7gJ1vdXUV6XSagyCoTk9P1zoywBJ//vz5a5588sn3isWiGu+ZdIx2" +
            "3MF0On0vgBsQlsWIJ4dmHMfuBaGjjSdSnEgotxVmTWKcfJqInp6amvp5W1FjFiJzc3O3MfO9d95552NEdC5W68eZQAA0nU5fC+CE" +
            "qk4izBhdtDKrpT9gqzGTfNh8ou8eQj+wrTAicpgZQRA0ZmdnH2xhgCX+woULXyCi0yJyCxEd9jzvXSL6h4kISBAH13XV931CmEE2" +
            "DLfjOUbkTwwDyWRSEvv9yjuilwmrYSYsNwBTDAGtxDuO8xNVzRFRRlU/A+CX8/Pzd5VKJSkUCm2SCoLAiUkx3W0RNryaBbG9ujQy" +
            "tuUy04tZA4gItVrNiTdFZW5uLg/gjKoeRlj+OgAcVT0kIj8ul8v1qampJ8z4iCjTY7NdI1ZVxCbdk7CaF7WKPM87SkT3A5g0ampz" +
            "eiAsiT8lIvd7nvfleB0AROWwdWJkwPFrd8m7fDARablczqnqAwA+j1DiNQA1hH0Ba59NIhoHcNrzvONEpLOzs5Yw0t45w57MKawJ" +
            "TBLR9QilnjaXDWe+qfZS4VC9lpkPxl+isSZqJ2iHXaK9YiLWU58FMAbgO+YzRURpICKONGwmvgvgzNra2jkAtLS0ZKs7H4BvqjlB" +
            "WNl90D7/rsAFQLlcrjkzM/NgNptlIvqeqn4Mofo7MLHe9AUeIqJfHD9+fMOofLLNbLutLUkOsHcknoSLqOFLvud5DwCoA/gugBvM" +
            "Ax9ARUR+NTY2dnpiYmIj2RAhokBEWkrohJPcZbLaEVtr9BPQ3hFqAnhobm4uo6r3ARgGsK6qDzcajTMTExMbhUKBk9I1L/NVNTAR" +
            "wYVpq1nsESbEo9rG+vq6tnSELJcuXbr0wMbGxgaArwP4y/Dw8E8nJycrhUKBS6VSm237vk/MnEbYaYn2B2NDeqW5mvjcEcSEIERE" +
            "Y2Nj4iYGqKrSkSNHGo8++ujDN99889/q9fobk5OTNZssxcfblpiqrgD4p3muAIIOsb+XD9hpp2kzUB+hMizl83nZSiotDq5Xaayq" +
            "9MILL4yq6jARUb1eRzqdbhlPPWwgpp47AiIiZibf9wMAqNVq795xxx3/6/qnq/14DLC9LTEqFosfGWaZHsf2aZ3Rlo/S9ZER1o7C" +
            "7uz0xY1ke2xhYSFdrVYPE9EwM9eZOUju9ARB0HUOx3F24xCFEwQBO46jvu+/cvTo0dc/kBrYvKBcLt+iqj8iooO6uSPU1m3q9q4O" +
            "ydV2Q01u4iA8KHXu8ccfL7kAMDMzkxkbG9vv+75bq9Wo2WzK6OhotKBqtUr79+9X+z2TyawdOXKkcfjwYatBwwAmVPVGVR1wHKdX" +
            "1Nt1iAhUtQHAYWZHVSfz+Ty7MzMzbjabvbdarU4TEadSqSCVSqFarba1tVVVmdmtVqt/9jzvkampKR8Iy93YtpnGDlbF0Ysju3KO" +
            "yACqSvl8Hu74+Li7srLyxWw2+yWbiySlZ1pcUFW4rotKpSJBEPyBiFZiY1wiShlGdFLnrgSKCD5oWOqVrFG4K6wi4gNozs7Owh0f" +
            "H68vLy//tlqtvhVLxgKEpW3cbu0pQ8dxnJl9+/a11AaGaa7RBlsQxRfQ1ca3yWS6MdBWg4KwcGsCgGu49vuXXnrpj8vLy3zgwIFg" +
            "aWlJgM1TYJlMhl577TU6dOgQAGBycrIOhI6tVCrZCRyrWrrDmxxboVc0MzU8GQGFErMPLVHmRS0eu1gsolgsbsndqDlgwqPh8p5s" +
            "gCThFgoFPnny5AlVnUbIEL9cLqexSYCePHlSPc+zx0tcz/OeqVQqTxSLRavWRERN0w9QTWyN2TE91vKhHJxyp6en9yGs++9yXZd8" +
            "3xdVpeTmplFvpFIp+L5/08jIyHOlUukdoDUKaNhAjTossf9/qNXgVnBXV1ebIyMja8z8ZhAEAczpEHNkLgn1fd8F8F8TUwFETdE9" +
            "2/frBHtGyT116lTD87xHiOjvCPfLHBGxJ7/bICKsqv/O5XLvGWcXNULNFR1I6keou5AJApubPWoX5wJALpe7AODCFb+VWcIcie3x" +
            "OiQs4HKwo8dvrTBMTcIABqJJdXNj87LfF8v8oKqLqvq7IAg+DUTSbDsq12OBO2o+sQjVMH7q6Xw+371C6xeqLVvgewra2qaP1vd/" +
            "V+aGcXBtIrkAAAAASUVORK5CYII=");
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

    /** KitKat battery icon for a level (0-100); charging uses the bolt version. */
    public static Drawable battery(Resources res, int level, boolean charging) {
        int[] st = {0, 15, 28, 43, 57, 71, 85, 100};
        int pick = 0;
        for (int i = 0; i < st.length; i++) {
            if (level >= st[i] - (i == 0 ? 0 : 7)) pick = st[i];
        }
        return get(res, (charging ? "c" : "b") + pick);
    }
}
