const rosu = require("rosu-pp");
const express = require("express");

const app = express();

app.get("/api/calcMax", (req, res) => {
  console.log(req.url);
  console.log("Connected");
  let mods;
  let bID;
  let maxCombo;
  let acc = 100;

  mods = parseInt(req.query.mods);
  bID = req.query.bID;
  maxCombo = parseInt(req.query.maxCombo);
  acc = parseFloat(req.query.acc);

  let arg = {
    path: "./beatmaps/" + bID + ".osu",
    params: [
      {
        mods: mods,
        acc: acc,
        nMisses: 0,
        combo: maxCombo,
      },
    ],
  };

  res.send(rosu.calculate(arg));
});

app.get("/api/calcCur", (req, res) => {
  console.log(req.url);
  console.log("Connected");

  let mods;
  let bID;
  let acc = 100;
  let mode = 0;
  let n300 = 0;
  let n100 = 0;
  let n50 = 0;
  let nMisses = 0;
  let nKatu;
  let maxCombo;
  let arg;

  if (mode == 0) {
    mods = parseInt(req.query.mods);
    bID = req.query.bID;
    acc = parseFloat(req.query.acc);
    mode = parseInt(req.query.mode);
    n300 = parseInt(req.query.n300);
    n100 = parseInt(req.query.n100);
    n50 = parseInt(req.query.n50);
    nMisses = parseInt(req.query.nMisses);
    maxCombo = parseInt(req.query.maxCombo);

    arg = {
      path: "./beatmaps/" + bID + ".osu",
      params: [
        {
          mode: mode,
          mods: mods,
          acc: acc,
          n300: n300,
          n100: n100,
          n50: n50,
          nMisses: nMisses,
          combo: maxCombo,
        },
      ],
    };
  } else {
    mods = parseInt(req.query.mods);
    bID = req.query.bID;
    acc = parseFloat(req.query.acc);
    mode = parseInt(req.query.mode);
    n300 = parseInt(req.query.n300);
    n100 = parseInt(req.query.n100);
    n50 = parseInt(req.query.n50);
    nMisses = parseInt(req.query.nMisses);
    maxCombo = parseInt(req.query.maxCombo);
    nKatu = parseInt(req.query.nKatu);

    arg = {
      path: "./beatmaps/" + bID + ".osu",
      params: [
        {
          mode: mode,
          mods: mods,
          acc: acc,
          n300: n300,
          n100: n100,
          n50: n50,
          nMisses: nMisses,
          combo: maxCombo,
          nKatu: nKatu,
        },
      ],
    };
  }

  console.log("Mode: " + mode);
  console.log("Acc: " + acc);
  console.log("n300: " + n300);
  console.log("n100: " + n100);
  console.log("n50: " + n50);
  console.log("nMisses: " + nMisses);
  console.log("maxCombo: " + maxCombo);

  res.send(rosu.calculate(arg));
});

app.listen(5000, () => {
  console.log("Listening");
});
