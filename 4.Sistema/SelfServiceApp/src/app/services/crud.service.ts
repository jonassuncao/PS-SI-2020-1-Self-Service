import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { ReadService } from './read.service';

export class CrudService<T> extends ReadService<T> {
  constructor(protected api: ApiService, protected url: string) {
    super(api, url);
  }

  public create(body: any): Observable<any> {
    return this.api.post(this.url, body);
  }

  public update(body: any): Observable<any> {
    return this.api.put(`${this.url}/${body.id}`, body);
  }

  public updateBody(id: string, body: any): Observable<any> {
    return this.api.put(`${this.url}/${id}`, body);
  }

  public delete(id: string): Observable<any> {
    return this.api.delete(`${this.url}/${id}`);
  }
}
