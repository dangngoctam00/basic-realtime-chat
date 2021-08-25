import {Pipe, PipeTransform} from "@angular/core";

@Pipe({name: 'reverse'})
export class ReverseArray implements PipeTransform {
  transform(value: any, ...args): any {
    console.log('reverse');
    console.log(value.reverse());
    return value.reverse();
  }
}
