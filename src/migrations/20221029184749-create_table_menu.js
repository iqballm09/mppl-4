'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    return queryInterface.createTable("menus", {
      id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: Sequelize.STRING(100),
        allowNull: false,
        validate: {
            notEmpty: { msg: "Menu must have a value" }
        }
    },
    price: {
        type: Sequelize.DECIMAL(20),
        allowNull: false,
        validate: {
            notEmpty: { msg: "Price must have a value" }
        }
    },
    // Tipe menu: makanan dan minuman
    type: {
        type: Sequelize.STRING(50),
        allowNull: false,
        validate: {
            notEmpty: { msg: "Menu must have value" },
            notNull: { msg: "Menu must not null" }
        }
    },
    createdAt: Sequelize.DATE,
    updatedAt: Sequelize.DATE    
    });
  },

  async down (queryInterface, Sequelize) {
    return queryInterface.dropTable("menus");
  }
};
