const Menu = require("../../models/Menu");
const Merchant = require("../../models/Merchant");
const dotenv = require("dotenv");

dotenv.config();

const getAllMenus = async (req, res) => {
    // Get payload
    const merchantID = req.merchant.id;
    // Get merchant by id
    const merchant = await Merchant.findOne({
        where: { id: merchantID }
    });
    // Get all menus
    try {
        const menus = await Menu.findAll({
            where: { MerchantID: merchant.id }
        });
        return res.status(200).json({ menus });
    } catch (error) {
        return res.status(500).send(error.message);
    }
}

const getMenuById = async (req, res) => {
    // Get payload
    const merchantID = req.merchant.id;
    // Read merchant by id
    const merchant = await Merchant.findOne({
        where: { id: merchantID }
    });
    // Get menu by id
    try {
        const menu = await Menu.findOne({
            where: {
                id: req.body.id,
                MerchantID: merchant.id
            }
        });
        if(!menu) return res.status(404).send(`Menu with id: ${ req.body.id } and MerchantID: ${ merchant.id } is not found`);
        return res.status(200).json({ menu });
    } catch (error) {
        return res.status(500).send(error.message);
    }
}

module.exports = {
    getAllMenus,
    getMenuById
}