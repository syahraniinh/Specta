const express = require('express');
const router = express.Router();
const specta = require('../models/specta');
const { ObjectId } = require('mongodb')

// Route GET untuk mendapatkan semua data tempat cafe 
router.get('/get', async (req, res) => {
  try {
    const tempatCafe = await specta.find();
    res.json({success: 1, message: 'Data tempat cafe berhasil ditampilkan', data:tempatCafe});
  } catch (err) {
    res.status(500).json({ success:0, message : err.message });
  }
});

// Route GET untuk mendapatkan data tempat cafe  berdasarkan ID
router.get('/:id', async (req, res) => {
  try {
    const tempatCafe = await specta.findById(req.params.id);
    if (!tempatCafe) {
      return res.status(404).json({success:1, message: ' Data tempat Cafe ditemukan',data:tempatCafe });
    }
    res.json(tempatCafe);
  } catch (err) {
    res.status(500).json({ success:0,message: 'data tempat cafe tidak ditemukan '});
  }
});

// Route POST untuk membuat data tempat cafe baru
router.post('/add', async (req, res) => {
  const tempatCafe = new specta({
    nama_cafe: req.body.nama_cafe,
    lokasi_cafe: req.body.lokasi_cafe,
    rating_cafe: req.body.rating_cafe,
    link_foto: req.body.link_foto,
    jam_operasional: req.body.jam_operasional,
    tentang_cafe: req.body.tentang_cafe,
    cafe_id:req.body.cafe_id
  });

  try {
    const newtempatCafe = await tempatCafe.save();
    res.status(201).json({message: 'Data tempat cafe berhasil ditambahkan', data: newtempatCafe});
  } catch (err) {
    res.status(400).json({ message: 'Gagal menambahkan tempat cafe', error: err.message });
  }
});

// Route PUT untuk memperbarui data tempat cafe berdasarkan ID
router.put('/update/:id', async (req, res) => {
  try {
    const tempatCafe = await specta.findById(req.params.id);
    if (!tempatCafe) {
      return res.status(404).json({ message: 'Data tempat Cafe tidak ditemukan' });
    }

    tempatCafe.cafe_id = req.body.cafe_id;
    tempatCafe.nama_cafe = req.body.nama_cafe;
    tempatCafe.lokasi_cafe = req.body.lokasi_cafe;
    tempatCafe.rating_cafe = req.body.rating_cafe;
    tempatCafe.link_foto = req.body.link_foto;
    tempatCafe.tentang_cafe = req.body.tentang_cafe;
    tempatCafe.jam_operasional = req.body.jam_operasional;
    tempatCafe.tempatCafe = req.body.tempatCafe;

    const updatedtempatCafe = await tempatCafe.save();
    res.json({ message: 'Data tempat cafe berhasil perbarui', data: updatedtempatCafe});
  } catch (err) {
    res.status(400).json({ message:'Gagal memperbarui data tempat cafe',error: err.message });
  }
});

// Route DELETE untuk menghapus data tempat cafe berdasarkan ID
router.delete('/delete/:id', async (req, res) => {
  try {
    const tempatCafe = await specta.findByIdAndDelete(new ObjectId(req.params.id));
    if (!tempatCafe) {
      return res.status(404).json({ success:0, message: 'Data tempat Cafe tidak ditemukan' });
    }

    res.json({success: 1, message: 'Data tempat Cafe berhasil dihapus' });
  } catch (err) {
    res.status(500).json({success:0, message: 'Gagal menghapus data tempat cafe', error: err.message });
  }
});

module.exports = router;