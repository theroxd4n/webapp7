import { Component, OnInit } from '@angular/core';
import {NGO} from '../../models/ngo';
import {Volunteering} from '../../models/volunteering';
import { Observable } from 'rxjs';
import {Router,ActivatedRoute,Params}from '@angular/router';
import { NgoService } from 'src/app/services/ngo.service';
import { VolunteeringService } from 'src/app/services/volunteering.service';
import { EntityService } from 'src/app/services/entity.service';
import { global } from '../../services/global';
@Component({
  selector: 'ngo-volunteerings',
  templateUrl: './ngo-volunteerings.component.html',
  styleUrls: ['./ngo-volunteerings.component.css'],
  providers:[NgoService,VolunteeringService,EntityService]
})
export class NgoVolunteeringsComponent implements OnInit {
  public NGO : NGO;
  public ngoid: number;
  public volList:Set<Volunteering>;
  public vollength : number;
  public url : string;
 
  constructor(
    private _route: ActivatedRoute,
    private _router:Router,
    private _ngoService:NgoService,
    private _volunteeringService:VolunteeringService,
    private _entityService: EntityService
  ) { 
      this.NGO=this._entityService.getIdentity();
      this.url=global.url;
  }

  ngOnInit() {
    this.getNGO();
  }

  getNGO(){
    this._ngoService.getNgo(this.NGO.id).subscribe(
      result =>{console.log(result),
      this.NGO= result 
      this.volList = this.NGO.volunteerings;
      console.log(this.volList);
  
    },
      error=>{console.log(<any>error)}
    );

  }

  
  deleteVol(volid:number){
    this._volunteeringService.deleteoneVolunteering(volid).subscribe(
      response=>{
        console.log(response);
        this.getNGO();
      },
      error=>{
        console.log(<any>error);
      }
    );
  }

}