/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Motivo;

/**
 *
 * @author carlos
 */
public interface GeneralMotivoDAO {
    public abstract ArrayList mostrarDatos();
    public abstract Motivo buscar(int idMotivo) ;
}
