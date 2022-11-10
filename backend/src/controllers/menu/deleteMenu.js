const Menu = require("../../models/Menu");
const Merchant = require("../../models/Merchant");
const dotenv = require("dotenv");

dotenv.config();

const deleteMenu = async (req, res) => {
    // Get Payload
    const merchantID = req.merchant.id;
    // Get merchant by id
    const merchant = await Merchant.findOne({
        where: { id: merchantID }
    });
    // Get menu by id
    Menu.destroy({
        where: {
            id: req.body.id,
            MerchantID: merchant.id
        }
    });
    // Get all menus
    const menus = await Menu.findAll({
        where: { MerchantID: merchant.id }
    });
    return res.status(200).json({ menus });    
}

module.exports = deleteMenu;