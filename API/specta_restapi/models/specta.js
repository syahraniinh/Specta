const mongoose = require('mongoose')

const spectaSchema = mongoose.Schema({
    nama_cafe:{
        type: String,
        required : true
    },
    lokasi_cafe:{
        type: String,
        required : true
    },
    rating_cafe:{
        type: String,
        required : true
    },
    link_foto:{
        type: String,
        required : true
    },
    jam_operasional:{
        type: String,
        required : true
    },
    tentang_cafe:{
        type: String,
        required : true
    },

}, {
    versionkey: false
})

module.exports = mongoose.model('specta',spectaSchema,'specta');