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
